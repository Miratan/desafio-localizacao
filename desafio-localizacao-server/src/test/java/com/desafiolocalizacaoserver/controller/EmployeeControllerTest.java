package com.desafiolocalizacaoserver.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafiolocalizacaoserver.model.Employee;
import com.desafiolocalizacaoserver.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        EmployeeController.class, EmployeeService.class
})
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private EmployeeController employeeController;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void findAll() {
		List<Employee> lista = new ArrayList<>();
		Employee e = generate();
		lista.add(e);

		Mockito.when(employeeService.findAll()).thenReturn(lista);

		List<Employee> employee = employeeController.findAll();

		Mockito.verify(employeeService, Mockito.times(1)).findAll();
		assertEquals(1, employee.size());
	}

	@Test
	public void findAllEmpty() {
		List<Employee> lista = new ArrayList<>();

		Mockito.when(employeeService.findAll()).thenReturn(lista);

		List<Employee> employee = employeeController.findAll();

		Mockito.verify(employeeService, Mockito.times(1)).findAll();
		assertNotEquals(1, employee.size());
	}

	private Employee generate() {
		Employee e = new Employee();
		e.setId(1L);
		e.setName("Nunes Sanfona");
		e.setLatitude(-27.589184);
		e.setLongitude(-48.520238);
		return e;
	}

}
