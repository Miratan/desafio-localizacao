package com.desafiolocalizacaoserver.model.dto;

public class PageableDTO {

	protected Long total;
	protected int page;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
