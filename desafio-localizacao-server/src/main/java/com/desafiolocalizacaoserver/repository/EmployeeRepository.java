package com.desafiolocalizacaoserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.desafiolocalizacaoserver.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
