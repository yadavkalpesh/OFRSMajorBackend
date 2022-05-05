package com.ofrs.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofrs.model.Flights;
import com.ofrs.service.FlightService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=FlightController.class)
class FlightControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;
	
	@Test
	void testAddFlight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllFlight() throws Exception {
		Flights flight1 = new Flights(1,"Indigo","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		Flights flight2 = new Flights(2,"Spice","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
	
		List<Flights> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		
		Mockito.when(flightService.getAllFlight()).thenReturn(flightList);
		
		String URI = "/getAllFlight";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(flightList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		//assertThat(outputInJson, expectedJson);
		//assertEquals(outputInJson, expectedJson);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	void testDeleteFlight() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateFlight() {
		fail("Not yet implemented");
	}

	@Test
	void testSearchFlight() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByFlightName() {
		fail("Not yet implemented");
	}

}
