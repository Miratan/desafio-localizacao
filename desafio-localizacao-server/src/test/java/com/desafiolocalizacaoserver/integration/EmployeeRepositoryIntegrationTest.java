package com.desafiolocalizacaoserver.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void findAll() {
		List<Employee> data = new ArrayList<Employee>();
		for (int i = 0; i < 10; i++) {
			Employee e = generateEmployee();
			data.add(e);
			entityManager.persist(e);
		}
		entityManager.flush();

		List<Employee> employees = employeeRepository.findAll();

		assertEquals(10, employees.size());
	}

	@Test
	public void findById() {
		Employee e = generateEmployee();
		e.setName("Miratan Lehmkuhl");
		entityManager.persist(e);
		for (int i = 0; i < 10; i++) {
			e = generateEmployee();
			entityManager.persist(e);
		}
		entityManager.flush();

		Employee employee = employeeRepository.findByName("Miratan Lehmkuhl");

		assertEquals("Miratan Lehmkuhl", employee.getName());
	}

	private Employee generateEmployee() {
		Employee e = new Employee();
		e.setName("Nunes Sanfona");
		e.setLatitude(-27.589184);
		e.setLongitude(-48.520238);
		return e;
	}	

}
