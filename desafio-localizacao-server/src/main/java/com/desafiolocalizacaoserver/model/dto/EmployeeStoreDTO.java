package com.desafiolocalizacaoserver.model.dto;

import java.io.Serializable;
import java.util.List;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.model.Store;

public class EmployeeStoreDTO implements Serializable, Comparable<EmployeeStoreDTO> {

	private static final long serialVersionUID = 1L;

	private Employee employee;
	private List<Store> stores;

	public EmployeeStoreDTO() {
	}

	public EmployeeStoreDTO(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public String toString() {
		return "EmployeeStoreDTO [employee=" + employee + ", stores=" + stores + "]";
	}

	@Override
	public int compareTo(EmployeeStoreDTO o) {
		return this.employee.getName().compareToIgnoreCase(o.employee.getName());
	}

}