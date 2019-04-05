package com.desafiolocalizacaoserver.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

import com.desafiolocalizacaoserver.model.Store;
import com.desafiolocalizacaoserver.service.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        StoreController.class, StoreService.class
})
@AutoConfigureMockMvc
public class StoreControllerTest {

	@Autowired
	private StoreController storeController;

	@MockBean
	private StoreService storeService;

	@Test
	public void findAll() {
		List<Store> lista = new ArrayList<>();
		Store s = generate();
		lista.add(s);

		Mockito.when(storeService.findAll()).thenReturn(lista);

		List<Store> stores = storeController.findAll();

		Mockito.verify(storeService, Mockito.times(1)).findAll();
		assertSame(s, stores.get(0));
		assertEquals(1, stores.size());
		assertEquals(s.getName(), stores.get(0).getName());
		assertTrue(s.getLatitude() == stores.get(0).getLatitude());
		assertTrue(s.getLongitude() == stores.get(0).getLongitude());
	}

	@Test
	public void findAllEmpty() {
		List<Store> lista = new ArrayList<>();

		Mockito.when(storeService.findAll()).thenReturn(lista);

		List<Store> stores = storeController.findAll();

		Mockito.verify(storeService, Mockito.times(1)).findAll();
		assertNotEquals(1, stores.size());
	}

	private Store generate() {
		Store s = new Store();
		s.setId(1L);
		s.setName("Angeloni Capoeiras");
		s.setLatitude(-27.589184);
		s.setLongitude(-48.520238);
		return s;
	}

}
