package com.desafiolocalizacaoserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.service.EmployeeService;

@RestController
@RequestMapping(value = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Employee>> findAll(){
    	logger.info("Chamada para buscar todos os representantes.");
        return ResponseEntity.ok().body(employeeService.findAll());
    }

}
