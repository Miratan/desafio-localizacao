package com.desafiolocalizacaoserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.model.dto.EmployeesDTO;
import com.desafiolocalizacaoserver.service.EmployeeService;
import com.desafiolocalizaoserver.enums.RoutesSort;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> findAll(){
    	logger.info("Chamada para buscar todos os representantes.");
        return employeeService.findAll();
    }

	@GetMapping(path = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeesDTO find(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
		logger.info("Chamada para buscar representantes paginados.");
		return employeeService.find(PageRequest.of(page, 5));
	}

	@GetMapping(path = "/{id}/stores", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeStoreDTO findStoresByEmployeeId(@PathVariable(value = "id") Long employeeId,
			@RequestParam(value = "sort", defaultValue = "PROXIMITY") RoutesSort sort) throws Exception {
		logger.info("Chamada para buscar latitude/longitude do representante e lojas.");
		return employeeService.findStoresByEmployeeId(employeeId, sort);
	}

}
