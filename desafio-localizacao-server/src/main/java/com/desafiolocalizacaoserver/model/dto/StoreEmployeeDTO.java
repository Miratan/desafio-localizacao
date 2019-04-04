package com.desafiolocalizacaoserver.model.dto;

import java.io.Serializable;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;

public class StoreEmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Store store;
	private Employee employee;

	public StoreEmployeeDTO() {
	}

	public StoreEmployeeDTO(Store store, Employee employee) {
		this.store = store;
		this.employee = employee;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
