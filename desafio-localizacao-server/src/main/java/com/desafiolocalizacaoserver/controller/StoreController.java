package com.desafiolocalizacaoserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.service.StoreService;

@RestController
@RequestMapping(value = "stores", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreController {

	Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<Store>> findAll() {
		logger.info("Chamada para buscar todas as lojas.");
		return ResponseEntity.ok().body(storeService.findAll());
	}

}
