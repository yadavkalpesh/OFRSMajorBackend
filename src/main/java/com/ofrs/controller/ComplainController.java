package com.ofrs.controller;

import java.util.List;

import java.util.Optional;
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
import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Complain;
import com.ofrs.service.ComplainService;
import ch.qos.logback.classic.Logger;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ComplainController {
	
	/*
	 * @Autowired enables dependency injection implicitly
	 * @Logger is to generate the log file report for each method being called
	 * @CrossOrigin enables cross-origin resource sharing only for this specific method.
	 * @RestController enables REST web services
	 * @Logger is to generate the log file report for each method being called
	 * 
	 * */

	@Autowired
	private ComplainService complainService;
	
	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(ComplainController.class);
	
	/*
	 * Add complain to database.
	 * @PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI).
	 * 
	 * */
	@PostMapping("/addComplain")
	public ResponseEntity<String> addComplain(@RequestBody Complain complain){
		complainService.addComplain(complain);
		logger.info("Add complain controller called....");
		return new ResponseEntity<String>("complain added!!!",HttpStatus.OK);
	}
	/******************************************************************************************************************/
	
	
	/*
	 * Fetch all complain from database.
	 * @ResponseEntity represents an HTTP response, including headers, body, and status.
	 * 
	 * */
	
	@GetMapping("/getAllComplain")
	public ResponseEntity<List<Complain>> getAllComplain(){
		List<Complain> compalinList = complainService.getAllComplain();
		logger.info("Get all complain controller called.....");
		return new ResponseEntity<List<Complain>>(compalinList,HttpStatus.OK);
	}
	
	/*
	 * Fetch complainById from database.
	 * @RecordNotFoundException will show exception message if the condition is true.
	 *  
	 * */

	@GetMapping("/getComplain/{complainId}")
	public ResponseEntity<?> getComplainById(@Valid @PathVariable int complainId){
		
		Optional<?> complain = complainService.getAllComplain(complainId);
		
		if(complain == null) {
			throw  new RecordNotFoundException("No record found....");
		}
		logger.info("Get complain by id controller called.....");
		return new ResponseEntity<>(complain,HttpStatus.OK);
	}
	
	
	/*
	 * Delete complainById from database.
	 * 
	 * */
	@DeleteMapping("/deleteComplain/{complainId}")
	public ResponseEntity<String> deleteBookingById(@Valid @PathVariable int complainId){
		
		complainService.deleteComplainById(complainId);
		if(complainId <= 0) {
			throw new RecordNotFoundException("No reccord found....");
		}
		logger.info("Delete complain controller called......");
		return new ResponseEntity<String>("Complain deleted succesfully.....",HttpStatus.OK);
	}
	
	
	/*
	 * Update complain using complainId.
	 * @PutMapping handle the HTTP POST requests matched with given URI expression.
	 * 
	 * */
	@PutMapping("/updateComplain/{complainId}")
	public ResponseEntity<String> updateComplainById(@Valid @PathVariable int complainId, @RequestBody Complain complain){
		
		complain.setComplainId(complainId);
		complainService.updateComplainById(complainId,complain);
		logger.info("Update complain controller called......");
		return new ResponseEntity<String>("Complain details updated successfully...",HttpStatus.OK);
		
	}
	

	/*
	 * Get all complain using UserId
	 * @PutMapping handle the HTTP POST requests matched with given URI expression.
	 * 
	 * */
	@GetMapping("/getAllComplainByUserId")
	public ResponseEntity<List<Complain>> getComplainByUserId(@RequestParam int userId){
		logger.info("getComplainByUserId Controller is called********");
		List<Complain> list = complainService.getComplainByUserId(userId);
		return new ResponseEntity<List<Complain>>(list,HttpStatus.OK);
	}
	
	

}
