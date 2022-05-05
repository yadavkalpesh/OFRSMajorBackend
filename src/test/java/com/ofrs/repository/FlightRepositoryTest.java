package com.ofrs.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FlightRepositoryTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Autowired
	private TestEntityManager entitymanger;
	
	@Autowired
	private FlightRepository flightrepo;
	
	

}
