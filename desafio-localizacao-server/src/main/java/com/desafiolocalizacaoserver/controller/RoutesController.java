package com.desafiolocalizacaoserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.service.RouteService;
import com.desafiolocalizaoserver.enums.RoutesSort;

@RestController
@RequestMapping(value = "routes")
public class RoutesController {

	@Autowired
	private RouteService routeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeStoreDTO> routeSort(@RequestParam(value = "sort", defaultValue = "PROXIMITY") RoutesSort sort) {
		return routeService.routesBy(sort);
	}

}
