package com.desafiolocalizacaoserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public void initialStore(List<Employee> entities) {
		logger.info("Gravando dados do arquivo funcionarios.csv no banco.");
		employeeRepository.saveAll(entities);
	}

	public Iterable<Employee> findAll() {
		logger.info("Buscando todas as lojas.");
		return employeeRepository.findAll();
	}

}
