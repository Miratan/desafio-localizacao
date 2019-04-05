package com.desafiolocalizacaoserver.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Distance;
import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.model.dto.EmployeeByStoreDistanceDTO;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.model.dto.StoreEmployeeDTO;
import com.desafiolocalizacaoserver.utils.RouteUtils;
import com.desafiolocalizaoserver.enums.RoutesSort;

@Service
public class RouteService {

	Logger logger = LoggerFactory.getLogger(RouteService.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private StoreService storeService;

	public Set<Distance> routes() {
		Set<Employee> employees = employeeService.employeesSet();
		Set<Store> stores = storeService.storesSet();

		Set<Distance> distances = new HashSet<>();

		logger.info("Busca todos os representantes.");
		logger.info("Busca todas as lojas.");
		logger.info("Ignora representante quando distância for superior a 2km da loja.");

		for (Employee employee : employees) {
			for (Store store : stores) {
				Double distance = RouteUtils.distance(employee.getLatitude(), employee.getLongitude(),
						store.getLatitude(), store.getLongitude());
				// Ignore distance when greater than 2 km
				if (distance > RouteUtils.MAX_DISTANCE_CONSIDERED) {
					continue;
				}
				distances.add(new Distance(distance, employee, store));
			}
		}

		return distances;
	}

	public Map<Store, Distance> routesByBiggerProximity() {
		Map<Store, Distance> map = new HashMap<Store, Distance>();
		for (Distance distance : routes()) {
			Distance mappedDistance = map.get(distance.getStore());
			if (mappedDistance == null) {
				map.put(distance.getStore(), distance);
			} else if (mappedDistance.getDistance() > distance.getDistance()) {
				mappedDistance.setDistance(distance.getDistance());
				mappedDistance.setEmployee(distance.getEmployee());
				mappedDistance.setStore(distance.getStore());
			}
		}

		logger.info("Cria relação do representante com maior proximidade a loja.");
		logger.info("Quando houver mais de um representante, o mais próximo fica como responsável pela loja.");

		return map;
	}

	public List<EmployeeStoreDTO> routesByBiggerProximityGroupedByEmployee() {
		Map<Employee, List<Store>> map = new TreeMap<Employee, List<Store>>();

		Map<Store, Distance> routesByBiggerProximity = routesByBiggerProximity();
		Set<Entry<Store, Distance>> entrySet = routesByBiggerProximity.entrySet();

		logger.info("Cria relação agrupando representante e lojas.");

		for (Entry<Store, Distance> entry : entrySet) {
			List<Store> stores = map.get(entry.getValue().getEmployee());
			if (stores == null) {
				stores = new ArrayList<Store>();
				stores.add(entry.getKey());
			} else {
				stores.add(entry.getKey());
			}
			map.put(entry.getValue().getEmployee(), stores);
		}

		logger.info("Ordena lojas para apresentação em ordem alfabética.");

		List<EmployeeStoreDTO> lista = new ArrayList<>();
		for (Entry<Employee, List<Store>> entry : map.entrySet()) {
			Collections.sort(entry.getValue());
			lista.add(new EmployeeStoreDTO(entry.getKey(), entry.getValue()));
		}

		return lista;
	}

	public List<StoreEmployeeDTO> routesDistributed() {
		Set<Store> stores = storeService.storesSet();
		List<Store> storesList = new ArrayList<Store>(stores);
		List<EmployeeByStoreDistanceDTO> employees = employeeService.employeesSet().stream()
				.map(employee -> new EmployeeByStoreDistanceDTO(employee)).collect(Collectors.toList());

		List<StoreEmployeeDTO> storeEmployeeDTO = new ArrayList<StoreEmployeeDTO>();
		
		logger.info("Cria estrutura para guardar quantidade de lojas visitadas.");
		logger.info("Ignora representante quando distância for superior a 2km da loja.");
		logger.info("Ordena representantes por quem está mais perto e possui menor número de lojas para atender.");

		Collections.sort(storesList);

		for (Store store : storesList) {
			List<EmployeeByStoreDistanceDTO> employeesDistance = new ArrayList<EmployeeByStoreDistanceDTO>();
			for (EmployeeByStoreDistanceDTO employee : employees) {
				double distance = RouteUtils.distance(store.getLatitude(), store.getLongitude(), employee.getLatitude(), employee.getLongitude());
				if (distance > RouteUtils.MAX_DISTANCE_CONSIDERED) {
					continue;
				}
				employee.setDistance(distance);
				employeesDistance.add(employee);
			}

			// No employee close
			if (employeesDistance.size() == 0) {
				logger.info("Nenhum representante próximo à loja: {}!", store.getName());
				continue;
			}

			Collections.sort(employeesDistance, Comparator.comparing(e -> e.getDistance()));
			EmployeeByStoreDistanceDTO employee = Collections.min(employeesDistance, Comparator.comparing(e -> e.getAttendingNumbers()));
			employee.setAttendingNumbers(employee.getAttendingNumbers() + 1);
			storeEmployeeDTO.add(new StoreEmployeeDTO(store, employee));
		}

		return storeEmployeeDTO;
	}

	public List<EmployeeStoreDTO> routesDistributedGroupedByEmployee() {
		List<StoreEmployeeDTO> routesDistributed = routesDistributed();

		Map<Employee, List<Store>> map = new TreeMap<Employee, List<Store>>();
		for (StoreEmployeeDTO storeEmployeeDTO : routesDistributed) {
			if (map.containsKey(storeEmployeeDTO.getEmployee())) {
				map.get(storeEmployeeDTO.getEmployee()).add(storeEmployeeDTO.getStore());
			} else {
				map.put(storeEmployeeDTO.getEmployee(), new ArrayList<Store>(Arrays.asList(storeEmployeeDTO.getStore())));
			}
		}

		List<EmployeeStoreDTO> lista = new ArrayList<>();
		for (Entry<Employee, List<Store>> entry : map.entrySet()) {
			Collections.sort(entry.getValue());
			lista.add(new EmployeeStoreDTO(entry.getKey(), entry.getValue()));
		}

		logger.info("Agrupa por representante as lojas para visitação.");

		return lista;
	}

	public List<EmployeeStoreDTO> routesBy(RoutesSort sort) {
		List<EmployeeStoreDTO> list = new ArrayList<EmployeeStoreDTO>();

		switch (sort) {
		case PROXIMITY:
			list = routesByBiggerProximityGroupedByEmployee();
			break;
		case DIVIDED:
			list = routesDistributedGroupedByEmployee();
			break;
		default:
			list = routesByBiggerProximityGroupedByEmployee();
			break;
		}

		return list;
	}

}