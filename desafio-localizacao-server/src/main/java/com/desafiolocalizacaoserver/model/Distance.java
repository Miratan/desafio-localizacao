package com.desafiolocalizacaoserver.model;

import java.io.Serializable;

public class Distance implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double distance;
	private Store store;
	private Employee employee;

	public Distance() {
	}

	public Distance(Double distance, Employee employee, Store store) {
		this.distance = distance;
		this.employee = employee;
		this.store = store;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
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
