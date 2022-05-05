package com.ofrs.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofrs.exception.InputNotProvidedException;
import com.ofrs.exception.InvalidInputProvidedException;
import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Flights;
import com.ofrs.repository.FlightRepository;
//import com.sun.org.slf4j.internal.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
//import sun.util.logging.resources.logging;

@Service
@Slf4j
public class FlightService {

	//Logger logger = org.slf4j.LoggerFactory.getLogger(FlightService.class);

	@Autowired
	private FlightRepository flightservice;

	//adding flight to db
	public Flights addFlight(Flights flight) {	
		log.info("addFlight Service is called********");
		return flightservice.save(flight);	
		
	}

	//fetching all flights from db
	public List<Flights> getAllFlight() {
		log.info("getAllFlight Service is called********");	
		return flightservice.findAll();
	}

	//Delete flight by id from db
	public void deleteFlight(Long id) {
		log.info("deleteFlight Service is called********");
		flightservice.deleteById(id);	
	}

	//Update Flight Details
		public Flights updateFlight(Long id, Flights flight) {
			log.info("updateFlight Service is called********");
			return flightservice.save(flight);
		}


	//search flight with source, destination and departure Date
	public List<Flights> searchFlight(String source,String destination,String depatureDate){
		log.info("searchFlight Service is called********");
		return flightservice.searchFlight(source, destination, depatureDate);
	}


	// search flight according to flight Name
	public List<Flights> findByFlightName(String flightName) {
		log.info("findByFlightName Service is called********");
		return flightservice.findByFlightName(flightName);
	}

	//get Single Flight By Id
	public Flights getFlightById(Long id) {
		log.info("getFlightById Service is called********");
		return flightservice.findById(id).orElseThrow(()-> new RecordNotFoundException("Record Not Found for id :"+id));
	}


}
