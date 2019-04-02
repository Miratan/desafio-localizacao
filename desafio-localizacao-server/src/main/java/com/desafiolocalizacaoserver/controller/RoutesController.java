package com.desafiolocalizacaoserver.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Distance;
import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
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

    @RequestMapping(path = "/bigger-proximity", method = RequestMethod.GET)
    public ResponseEntity<Map<Store, Distance>> routesByBiggerProximity(){
        return ResponseEntity.ok().body(routeService.routesByBiggerProximity());
    }

    @RequestMapping(path = "/bigger-proximity-grouped-by-employee", method = RequestMethod.GET)
    public List<EmployeeStoreDTO> routesByBiggerProximityGroupedByEmployee(){
        return routeService.routesByBiggerProximityGroupedByEmployee();
    }

}
