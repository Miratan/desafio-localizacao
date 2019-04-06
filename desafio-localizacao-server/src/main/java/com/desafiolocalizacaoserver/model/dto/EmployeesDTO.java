package com.desafiolocalizacaoserver.model.dto;

import java.util.List;

import com.desafiolocalizacaoserver.model.Employee;

public class EmployeesDTO extends PageableDTO {

	private List<Employee> employees;

	public EmployeesDTO() {
	}

	public EmployeesDTO(List<Employee> employees, Long total, int page) {
		this.employees = employees;
		this.total = total;
		this.page = page;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
