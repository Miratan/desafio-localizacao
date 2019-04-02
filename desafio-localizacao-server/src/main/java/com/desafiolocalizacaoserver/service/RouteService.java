package com.desafiolocalizacaoserver.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Distance;
import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;
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

		logger.info("Busca todos os funcionários.");
		logger.info("Busca todas as lojas.");
		logger.info("Ignora funcionários com distância superior a 2km da loja.");

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

}