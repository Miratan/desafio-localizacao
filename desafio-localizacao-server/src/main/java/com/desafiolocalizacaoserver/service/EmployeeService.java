package com.desafiolocalizacaoserver.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.model.dto.EmployeesDTO;
import com.desafiolocalizacaoserver.repository.EmployeeRepository;
import com.desafiolocalizaoserver.enums.RoutesSort;

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

	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		Collections.sort(employees);
		return employees;
	}

	public Set<Employee> employeesSet() {
		return new HashSet<>(findAll());
	}

	public EmployeeStoreDTO findStoresByEmployeeId(Long employeeId, RoutesSort sort) throws Exception {
		List<EmployeeStoreDTO> lista = new ArrayList<EmployeeStoreDTO>();

		switch (sort) {
		case PROXIMITY:
			lista = routeService.routesByBiggerProximityGroupedByEmployee();
			break;
		case DIVIDED:
			lista = routeService.routesDistributedGroupedByEmployee();
			break;
		default:
			lista = routeService.routesByBiggerProximityGroupedByEmployee();
			break;
		}

		for (EmployeeStoreDTO employeeStoreDTO : lista) {
			if (employeeStoreDTO.getEmployee().getId() == employeeId) {
				return employeeStoreDTO;
			}
		}

		throw new Exception("Representante não encontrado.");
	}

	public EmployeesDTO find(Pageable pageable) {
		Page<Employee> employees = employeeRepository.findAll(pageable);
		return new EmployeesDTO(employees.getContent(), employees.getTotalElements(), employees.getNumber());
	}

}
