package com.desafiolocalizacaoserver.model.dto;

import java.io.Serializable;

import com.desafiolocalizacaoserver.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeByStoreDistanceDTO extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double distance;

	@JsonIgnore
	private int attendingNumbers;

	public EmployeeByStoreDistanceDTO() {
	}

	public EmployeeByStoreDistanceDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.latitude = employee.getLatitude();
		this.longitude = employee.getLongitude();
	}

	public EmployeeByStoreDistanceDTO(Employee employee, Double distance) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.latitude = employee.getLatitude();
		this.longitude = employee.getLongitude();
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getAttendingNumbers() {
		return attendingNumbers;
	}

	public void setAttendingNumbers(int attendingNumbers) {
		this.attendingNumbers = attendingNumbers;
	}

}
