package com.desafiolocalizacaoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiolocalizacaoserver.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByName(String name);

}
