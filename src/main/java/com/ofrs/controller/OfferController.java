package com.ofrs.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ofrs.exception.InputNotProvidedException;
import com.ofrs.exception.InvalidInputProvidedException;
import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Flights;
import com.ofrs.model.Offer;
import com.ofrs.service.OfferService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200/")
public class OfferController {

	/*
	 * 
	 * 
	 * @SpringToolSuit version 3.4.0
	 * @Date 15/04/2022
	 * @ClassName OfferController
	 * @OfferController.java holds all the CRUD operation related to offer in ticket
	 * @Autowired enables dependency injection implicitly
	 * 
	 * */

	
	@Autowired
	private OfferService offerservice;
	
	
	/*
	 * Adding offer to database
	 * * @PostMapping is a composed annotation that is used in place of @RequestMapping(method = RequestMethod. POST) . 
	 * @PostMapping annotated methods handle the HTTP POST requests matched with given URI expression.
	 * @ResponseEntity represents an HTTP response, including headers, body, and status.
	 * 
	 * */
	
		@PostMapping("/addOffer")
		public ResponseEntity<String> addOffer(@Valid @RequestBody Offer offer){
			log.info("addOffer Controller is called********");
			if(offer.getOfferName().isEmpty() || offer.getOfferName().length() ==0) {
				throw new InputNotProvidedException("Input Field are empty");
			}
			if(offer.getOfferAmount()<=0) {
				throw new InvalidInputProvidedException("Invalid Input Provided");
			}
			offerservice.addOffer(offer);
			return new ResponseEntity<String>("Offer added",HttpStatus.OK);
		}
		/****************************************************************************************************************************/
		
		/*
		 * Fetching all data from database
		 * @GetMapping mapping HTTP GET requests onto specific handler methods.
		 * 
		 * 
		 * */
		
		@GetMapping("/getAllOffer")
		public ResponseEntity<List<Offer>> getAllOffer(){
			log.info("getAllOffer Controller is called********");
			List<Offer> offerList = offerservice.getAllOffer();
			if(offerList.isEmpty()) {
				throw new RecordNotFoundException("No Data Found");
			}
			return new ResponseEntity<List<Offer>>(offerList,HttpStatus.OK);
		}
		/******************************************************************************************************************************/
		
		/*
		 * This method is used to delete offer from database using offerId.
		 * @DeleteMapping maps HTTP DELETE requests onto specific handler methods.
		 * 
		 * */
		
		
		//Delete Offer by id from db
		@DeleteMapping("/deleteOffer/{id}")
		public ResponseEntity<String> deleteOffer(@Valid @PathVariable int id){
			log.info("deleteOffer Controller is called********");
			if(id <= 0) {
				throw new InvalidInputProvidedException("Invalid Input Provided");
			}
			offerservice.deleteOffer(id);
			return new ResponseEntity<String>("offer deleted!!!",HttpStatus.OK);
		}
		/******************************************************************************************************************************/
		
		/*
		 *This method will update offer in database with offerId. 
		 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI). 
		 *@PutMapping handle the HTTP POST requests matched with given URI expression.
		 * 
		 * */
		
		
		
		@PutMapping("/updateOffer/{id}")
		public ResponseEntity<String> updateOffer(@Valid @PathVariable int id,@RequestBody Offer offer){
			log.info("updateOffer Controller is called********");
			if(offer.getOfferName().isEmpty() || offer.getOfferName().length() ==0) {
				throw new InputNotProvidedException("Input Field are empty");
			}
			if(offer.getOfferAmount()<=0) {
				throw new InvalidInputProvidedException("Invalid Input Provided");
			}
			offer.setOfferId(id);
			offerservice.updateOffer(id,offer);
			return new ResponseEntity<String>("Offer Updated!!",HttpStatus.OK);
		}
		
		/******************************************************************************************************************************/
		
		
		/*
		 *This method will fetch offer details from database using offerId. 
		 *@GetMapping mapping HTTP GET requests onto specific handler methods.
		 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI).
		 *
		 * */
		
		@GetMapping("/getOfferById/{id}")
		public ResponseEntity<Offer> getOfferById(@PathVariable int id){
			log.info("getOfferById Controller is called********");
			Offer offer = offerservice.getOfferById(id);
			return new ResponseEntity<Offer>(offer,HttpStatus.OK);
		}
		
		/******************************************************************************************************************************/

		/*
		 *This method will search offer details from database using offerName. 
		 *@GetMapping mapping HTTP GET requests onto specific handler methods.
		 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI).
		 *
		 * */
		
		
		@GetMapping("/searchOfferByName/{OfferName}")
		public ResponseEntity<List<Offer>> findByOfferName(@PathVariable String OfferName){
			log.info("findByOfferName Controller is called********");
			List<Offer> offerList = offerservice.findByOfferName(OfferName);
			return new ResponseEntity<List<Offer>>(offerList,HttpStatus.OK);
		}

}
