package com.ofrs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofrs.exception.InputNotProvidedException;
import com.ofrs.exception.InvalidInputProvidedException;
import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Flights;
import com.ofrs.service.FlightService;

import lombok.extern.slf4j.Slf4j;


/*
 * 
 * 
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * @ClassName FlightController
 * @FlightController.java holds all the CRUD operation related to booking ticket
 * 
 * 
 * */


@RestController
@Slf4j
@CrossOrigin("http://localhost:4200/")
public class FlightController {

	/*
	 * @Autowired enables dependency injection implicitly
	 * @Logger is to generate the log file report for each method being called
	 * @CrossOrigin enables cross-origin resource sharing only for this specific method.
	 * @RestController enables REST web services 
	 * @PostMapping annotated methods handle the HTTP POST requests matched with given URI expression.
	 * @Valid allows to validate object graphs with a single call to the validator.
	 *
	 * */
	
	@Autowired
	private FlightService flightservice;
	
	
	/*
	 * This method will add flight to database.
	 *  @PostMapping is a composed annotation that is used in place of @RequestMapping(method = RequestMethod. POST) .
	 *  @ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  
	 * */
	@PostMapping("/addFlight")
	public ResponseEntity<Flights> addFlight(@Valid @RequestBody Flights flight){
		log.info("addFlight Controller is called********");

		if(flight.getFlightName().isEmpty() || flight.getFlightName().length() ==0) {
			throw new InputNotProvidedException("Input Field are empty");
		}
		if(flight.getBasePrice()<=0 || flight.getTotalSeats()<=0) {
			throw new InvalidInputProvidedException("Invalid Input Provided");
		}
		flightservice.addFlight(flight);
		return new ResponseEntity<Flights>(flight,HttpStatus.OK);
	}
	
	/***********************************************************************************************/
	
	/*
	 * This method will fetch all flights from database
	 *@GetMapping mapping HTTP GET requests onto specific handler methods.
	 *@throw used to declare exceptions that can occur during the execution of a program.
	 *
	 * */
	@GetMapping("/getAllFlight")
	public ResponseEntity<List<Flights>> getAllFlight(){
		log.info("getAllFlight Controller is called********");
		List<Flights> flightList = flightservice.getAllFlight();
		if(flightList.isEmpty()) {
			throw new RecordNotFoundException("No Data Found");
		}
		return new ResponseEntity<List<Flights>>(flightList,HttpStatus.OK);
	}
	/***********************************************************************************************/
	
	/*
	 * This method will delete flight data from database
	 * @DeleteMapping maps HTTP DELETE requests onto specific handler methods.
	 * 
	 * */
	@DeleteMapping("/deleteFlight/{id}")
	public ResponseEntity<String> deleteFlight(@Valid @PathVariable Long id){
		log.info("deleteFlight Controller is called********");
		if(id <= 0) {
			throw new InvalidInputProvidedException("Invalid Input Provided");
		}
		flightservice.deleteFlight(id);
		return new ResponseEntity<String>("flight deleted!!!",HttpStatus.OK);
	}
	/***********************************************************************************************/
	
	/*
	 * This method will fetch flight details through flightId.
	 * @Log is to generate the log file report for each method being called
	 * 
	 * */
	@GetMapping("/getFlightById/{id}")
	public ResponseEntity<Flights> getFlightById(@PathVariable Long id){
		log.info("getFlightById Controller is called********");
		Flights flight = flightservice.getFlightById(id);
		return new ResponseEntity<Flights>(flight,HttpStatus.OK);
	}
	/***********************************************************************************************/
	/*
	 * This method will update flight details in database through flightId.
	 * @PutMapping handle the HTTP POST requests matched with given URI expression.
	 * @PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI).
	 * 
	 * */
	
	@PutMapping("/updateFlight/{id}")
	public ResponseEntity<String> updateFlight(@Valid @PathVariable Long id,@RequestBody Flights flight){
		log.info("updateFlight Controller is called********");
		if(flight.getFlightName().isEmpty() || flight.getFlightName().length() ==0) {
			throw new InputNotProvidedException("Input Field are empty");
		}
		if(flight.getBasePrice()<=0 || flight.getTotalSeats()<=0) {
			throw new InvalidInputProvidedException("Invalid Input Provided");
		}
		flight.setFlightId(id);
		flightservice.updateFlight(id,flight);
		return new ResponseEntity<String>("Flight Updated!!",HttpStatus.OK);
	}
	/***********************************************************************************************/
	
	/*
	 * Searching flight according to source, destination and depatureTime
	 * 
	 * */
	@GetMapping("/searchFlight")
	public ResponseEntity<List<Flights>> searchFlight(@RequestParam String source,@RequestParam String destination,@RequestParam String depatureDate){
		log.info("searchFlight Controller is called********");
		List<Flights> searchFlightList = flightservice.searchFlight(source, destination, depatureDate);
		return new ResponseEntity<List<Flights>>(searchFlightList,HttpStatus.OK);
	}
	
	//searching flight By Name
	@GetMapping("/searchFlightByName/{flightName}")
	public ResponseEntity<List<Flights>> findByFlightName(@PathVariable String flightName){
		log.info("searchFlightByName Controller is called********");
		List<Flights> flightList = flightservice.findByFlightName(flightName);
		return new ResponseEntity<List<Flights>>(flightList,HttpStatus.OK);
	}
	
}
