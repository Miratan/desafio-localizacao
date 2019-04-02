package com.desafiolocalizacaoserver.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Distance;
import com.desafiolocalizacaoserver.service.RouteService;

@RestController
@RequestMapping(value = "routes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoutesController {

	@Autowired
	private RouteService routeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Distance>> routes(){
        return ResponseEntity.ok().body(routeService.routes());
    }

}
