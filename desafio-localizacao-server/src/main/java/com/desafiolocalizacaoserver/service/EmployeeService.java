package com.desafiolocalizacaoserver.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RouteService routeService;

	public void initialStore(List<Employee> entities) {
		logger.info("Gravando dados do arquivo funcionarios.csv no banco.");
		employeeRepository.saveAll(entities);
	}

	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Set<Employee> employeesSet() {
		Iterable<Employee> employees = findAll();
		Set<Employee> employeesSet = new HashSet<>();
		employees.forEach(employeesSet::add);
		return employeesSet;
	}

	public EmployeeStoreDTO findStoresByEmployeeId(Long employeeId) throws Exception {
		List<EmployeeStoreDTO> routesByProximity = routeService.routesByBiggerProximityGroupedByEmployee();

		for (EmployeeStoreDTO employeeStoreDTO : routesByProximity) {
			if (employeeStoreDTO.getEmployee().getId() == employeeId) {
				return employeeStoreDTO;
			}
		}

		throw new Exception("Representante não encontrado.");
	}

}
