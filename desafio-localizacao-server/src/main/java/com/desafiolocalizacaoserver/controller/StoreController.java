package com.desafiolocalizacaoserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.model.dto.StoresDTO;
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

	@GetMapping(path = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoresDTO find(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
		logger.info("Chamada para buscar lojas paginadas.");
		return storeService.find(PageRequest.of(page, 5));
	}

}
