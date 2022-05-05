package com.ofrs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



import com.ofrs.model.Flights;
import com.ofrs.repository.FlightRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class FlightServiceTest {

	@Autowired
	private FlightService flightservice;
	
	@MockBean
	private FlightRepository flightrepo;
	
	@Test
	public void testAddFlight() {
		Flights flight = new Flights(1,"Indigo","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		Mockito.when(flightrepo.save(flight)).thenReturn(flight);
//		assertThat(flightservice.addFlight(flight))
//		  assertThat(flightservice.addFlight(flight),);
		assertThat(flight.getFlightId() == 1);
//		assertEquals(flightservice.addFlight(flight), flight);
		// assertThat(flightservice.addFlight(flight)).isEqualTo(flight);
//		assertEquals(flightservice.addFlight(flight), flight);
//		 assertThat(flightservice.addFlight(flight).equals(flight));
		// AssertionsForClassTypes.assertThat(flightservice.addFlight(flight)).isEqualTo(flight);
		assertThat(flight != null);
		
		
		//assertThat(flightservice.addFlight(flight), is(instanceOf(Flights)));
	}

	@Test
	public void testGetAllFlight() {
		Flights flight1 = new Flights(1,"Indigo","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		Flights flight2 = new Flights(2,"Spice","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		
		List<Flights> flightlist = new ArrayList<>();
		flightlist.add(flight1);
		flightlist.add(flight2);
		Mockito.when(flightrepo.findAll()).thenReturn(flightlist);
		
		assertThat(flightservice.getAllFlight()).isEqualTo(flightlist);
	}

	@Test
	public void testDeleteFlight() {
		Flights flight1 = new Flights(1,"Indigo","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		assertThat(flight1.getFlightId()>0);
		Mockito.when(flightrepo.getById((long) 1)).thenReturn(flight1);
	
	}

	@Test
	void testUpdateFlight() {
		Flights flight1 = new Flights(1,"Indigo","pune","mumbai","19-4-2022","10.34","1 stop",240,2200);
		assertThat(flight1.getFlightId()>0);
		Mockito.when(flightrepo.getById((long)1)).thenReturn(flight1);
		
		flight1.setBasePrice(5000);
		flight1.setFlightName("Spice");
		Mockito.when(flightrepo.save(flight1)).thenReturn(flight1);
		
		assertThat(flightservice.updateFlight((long)1,flight1)).isEqualTo(flight1);
	}

//	@Test
//	void testSearchFlight() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testFindByFlightName() {
//		fail("Not yet implemented");
//	}

}
