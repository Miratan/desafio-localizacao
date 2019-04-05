package com.desafiolocalizacaoserver.controller;

import static org.junit.Assert.assertEquals;

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
import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.model.dto.EmployeeStoreDTO;
import com.desafiolocalizacaoserver.service.RouteService;
import com.desafiolocalizaoserver.enums.RoutesSort;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		RouteController.class, RouteService.class
})
@AutoConfigureMockMvc
public class RoutesControllerTest {

	@Autowired
	private RouteController routeController;

	@MockBean
	private RouteService routeService;

	@Test
	public void routeSort() {
		List<EmployeeStoreDTO> lista = new ArrayList<EmployeeStoreDTO>();
		EmployeeStoreDTO e = generate();
		lista.add(e);

		Mockito.when(routeService.routesBy(RoutesSort.PROXIMITY)).thenReturn(lista);

		List<EmployeeStoreDTO> routes = routeController.routesBy(null);
		Mockito.verify(routeService, Mockito.times(1)).routesBy(null);
		assertEquals(0, routes.size());

		routes = routeController.routesBy(RoutesSort.PROXIMITY);
		Mockito.verify(routeService, Mockito.times(1)).routesBy(RoutesSort.PROXIMITY);

		assertEquals(1, routes.size());
		assertEquals(3, routes.get(0).getStores().size());
		assertEquals("Nunes Sanfona", routes.get(0).getEmployee().getName());
		assertEquals("Limeira", routes.get(0).getStores().get(1).getName());
	}

	private EmployeeStoreDTO generate() {
		return new EmployeeStoreDTO(employee(), stores());
	}

	private Employee employee() {
		Employee e = new Employee();
		e.setId(1L);
		e.setName("Nunes Sanfona");
		e.setLatitude(-27.589184);
		e.setLongitude(-48.520238);
		return e;
	}

	private List<Store> stores() {
		String[] names = {"Angeloni", "Limeira", "Imperatriz"};
		List<Store> stores = new ArrayList<Store>();

		for (String name : names) {
			Store s = new Store();
			s.setName(name);
			s.setLatitude(-27.589184);
			s.setLongitude(-48.520238);
			stores.add(s);
		}

		return stores;
	}

}
