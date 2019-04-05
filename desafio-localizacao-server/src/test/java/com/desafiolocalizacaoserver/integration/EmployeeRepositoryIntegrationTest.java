package com.desafiolocalizacaoserver.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public void getAll() {
		// given
		List<Employee> data = new ArrayList<Employee>();
		for (int i = 0; i < 10; i++) {
			Employee e = generateEmployee();
			data.add(e);
			entityManager.persist(e);
		}
		entityManager.flush();

		// when
		Iterable<Employee> employees = employeeRepository.findAll();
		Set<Employee> employeesSet = new HashSet<>();
		employees.forEach(employeesSet::add);

		// then
		assertEquals(10, employeesSet.size());
	}

	@Test
	public void findById() {
		// given
		Employee e = generateEmployee();
		e.setName("Miratan Lehmkuhl");
		entityManager.persist(e);
		for (int i = 0; i < 10; i++) {
			e = generateEmployee();
			entityManager.persist(e);
		}
		entityManager.flush();

		// when
		Employee employee = employeeRepository.findByName("Miratan Lehmkuhl");

		// then
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
