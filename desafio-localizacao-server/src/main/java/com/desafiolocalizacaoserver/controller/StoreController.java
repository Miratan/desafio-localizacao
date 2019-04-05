package com.desafiolocalizacaoserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.service.StoreService;

@RestController
@RequestMapping(value = "stores")
public class StoreController {

	Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Store> findAll() {
		logger.info("Chamada para buscar todas as lojas.");
		return storeService.findAll();
	}

}
