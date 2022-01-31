
package com.brewery;

import com.brewery.controller.BreweryController;
import com.brewery.service.BreweryService;

import com.lib.brewery.models.Brewery;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;


@WebMvcTest(BreweryController.class)
public class BreweryApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BreweryService breweryService;


	@Test
	public void testGetBrewery() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("madtree-brewing-cincinnati");
		Mockito.when(breweryService.getBrewery(any(String.class))).thenReturn(breweryStub);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<Brewery> res = breweryController.getBrewery("madtree-brewing-cincinnati");
		assertEquals("Equal", "madtree-brewing-cincinnati", res.getBody().getId());

	}

	@Test
	public void testGetBreweryNotEqual() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("madtree");
		Mockito.when(breweryService.getBrewery(any(String.class))).thenReturn(breweryStub);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<Brewery> res = breweryController.getBrewery("madtree-brewing-cincinnati");
		assertNotEquals("Not Equal", "madtree-brewing-cincinnati", res.getBody().getId());

	}

	@Test
	public void testSearchBrewery() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("treehouse-brewing-texas");
		breweryStub.setName("texas-brewery");
		List<Brewery> breweryList = new ArrayList<Brewery>();
		breweryList.add(breweryStub);
		Mockito.when(breweryService.searchBrewery(any(String.class))).thenReturn(breweryList);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<List<Brewery>> res = breweryController.searchBrewery("texas");
		assertEquals("Equal", "texas-brewery", res.getBody().get(0).getName());

	}

	@Test
	public void testSearchBreweryNotEqual() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("treehouse-brewing-texas");
		breweryStub.setName("texas-brewery");
		List<Brewery> breweryList = new ArrayList<Brewery>();
		breweryList.add(breweryStub);
		Mockito.when(breweryService.searchBrewery(any(String.class))).thenReturn(breweryList);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<List<Brewery>> res = breweryController.searchBrewery("texas");
		assertNotEquals("Not Equal", "texas", res.getBody().get(0).getName());

	}

	@Test
	public void testSearchBreweryByState() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("roll-brewing-ohio");
		breweryStub.setState("ohio");
		List<Brewery> breweryList = new ArrayList<Brewery>();
		breweryList.add(breweryStub);
		Mockito.when(breweryService.getBreweryByState(any(String.class))).thenReturn(breweryList);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<List<Brewery>> res = breweryController.searchBreweryByState("ohio");
		assertEquals("Equal", "ohio", res.getBody().get(0).getState());

	}

	@Test
	public void testSearchBreweryByStateNotEqual() {
		// prepare data and mock's behaviour
		Brewery breweryStub = new Brewery();
		breweryStub.setId("roll-brewing-ohio");
		breweryStub.setState("ohio");
		List<Brewery> breweryList = new ArrayList<Brewery>();
		breweryList.add(breweryStub);
		Mockito.when(breweryService.getBreweryByState(any(String.class))).thenReturn(breweryList);

		BreweryController breweryController = new BreweryController(breweryService);
		ResponseEntity<List<Brewery>> res = breweryController.searchBreweryByState("ohio");
		assertNotEquals("Equal", "texas", res.getBody().get(0).getState());

	}

}

