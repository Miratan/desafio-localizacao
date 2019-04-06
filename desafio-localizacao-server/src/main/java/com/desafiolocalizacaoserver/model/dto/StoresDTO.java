package com.desafiolocalizacaoserver.model.dto;

import java.util.List;

import com.desafiolocalizacaoserver.model.Store;

public class StoresDTO extends PageableDTO {

	private List<Store> stores;

	public StoresDTO() {
	}

	public StoresDTO(List<Store> stores, Long total, int page) {
		this.stores = stores;
		this.total = total;
		this.page = page;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

}
