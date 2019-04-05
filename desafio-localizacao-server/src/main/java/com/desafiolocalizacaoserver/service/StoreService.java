package com.desafiolocalizacaoserver.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.repository.StoreRepository;

@Service
public class StoreService {

	Logger logger = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	private StoreRepository storeRepository;

	public void initialStore(List<Store> entities) {
		logger.info("Gravando dados do arquivo lojas.csv no banco.");
		storeRepository.saveAll(entities);
	}

	public List<Store> findAll() {
		List<Store> stores = storeRepository.findAll();
		Collections.sort(stores);
		return stores;
	}

	public Set<Store> storesSet() {
		return new HashSet<>(findAll());
	}

}
