package com.desafiolocalizacaoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiolocalizacaoserver.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
