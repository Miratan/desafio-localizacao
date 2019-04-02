package com.desafiolocalizacaoserver.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Distance;
import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.utils.RouteUtils;

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
				Double distance = RouteUtils.distance(employee.getLatitude(), employee.getLongitude(), store.getLatitude(), store.getLongitude());
				// Ignore distance when greater than 2 km
				if (distance > 2D) {
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
			EmployeeStoreDTO employeeStoreDTO = new EmployeeStoreDTO();
			employeeStoreDTO.setEmployee(entry.getKey());
			employeeStoreDTO.setStores(entry.getValue());
			lista.add(employeeStoreDTO);
		}

		return lista;
	}

}