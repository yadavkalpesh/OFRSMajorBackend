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
import com.ofrs.model.BookTicket;
import com.ofrs.model.Offerdto;
import com.ofrs.model.RegisterUser;
import com.ofrs.service.BookingTicketService;
import com.ofrs.service.EmailSenderService;
import ch.qos.logback.classic.Logger;



/*
 * 
 * 
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * @ClassName BookingTicketController
 * @BookingTicketController.java holds all the CRUD operation related to booking ticket
 * 
 * 
 * */

@RestController
@CrossOrigin("http://localhost:4200/")
public class BookingTicketController {
	
	/*
	 * @Autowired enables dependency injection implicitly
	 * @Logger is to generate the log file report for each method being called
	 * @CrossOrigin enables cross-origin resource sharing only for this specific method.
	 * @RestController enables REST web services
	 * 
	 * */
	
	@Autowired
	BookingTicketService bookTicketService;
	
	@Autowired
	private EmailSenderService senderService;
	
	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(BookingTicketController.class);
	
	
	/*************************************************************************************************************************/
	
	
	/*
	 * This post method will Add new booking to database
	 * @PostMapping is a composed annotation that is used in place of @RequestMapping(method = RequestMethod. POST) . 
	 * @PostMapping annotated methods handle the HTTP POST requests matched with given URI expression.
	 * @Valid allows to validate object graphs with a single call to the validator.
	 * @RequestBody used to convert Body of HTTP request and response to Java class object.
	 * @logger info will print when this handler method is called.
	 * @ResponseEntity represents an HTTP response, including headers, body, and status.
	 * 
	 * */
	
	@PostMapping("/newBooking")
	public ResponseEntity<?> addBooking(@Valid @RequestBody Offerdto odto, RegisterUser reg)
	{	
		BookTicket bookModel = Offerdto.toOfferModel(odto); 
		bookTicketService.addBooking(bookModel);
		logger.info("New create booking controller called....");
		senderService.sendEmail("ashiphs@cybage.com", "Passenger Name: " + odto.getTotalPassenger() + "\nTotal Amount: " + odto.getTotalAmount() + "\nFlight ID: " + odto.getFlightId(),"Booking Successful");
		return new ResponseEntity<String>("New booking added successfully.....",HttpStatus.OK);
	}

	
	/*****************************************************************************************************************************/
	
	/*
	 *This method will fetch all bookings from database
	 *@GetMapping mapping HTTP GET requests onto specific handler methods.
	 *@throw used to declare exceptions that can occur during the execution of a program.
	 *@RecordNotFoundException will show exception message if the condition is true.
	 * 
	 * */
	
	@GetMapping("/getAllBookings")
	
	public ResponseEntity<List<BookTicket>> getAllBookings()
	{
		List<BookTicket> list = bookTicketService.getAllBookings();
		
		if(list.isEmpty()) {
			throw new RecordNotFoundException("No data found....");
		}
		logger.info("Get all booking controller called....");
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	/*********************************************************************************************************************************/
	
	/*
	 *This method will update booking in database with bookingId. 
	 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI). 
	 *@PutMapping handle the HTTP POST requests matched with given URI expression.
	 * 
	 * */
	
	
	@PutMapping("/updateBooking/{id}")
	public ResponseEntity<String> updateBookingById(@Valid @PathVariable int id,@RequestBody BookTicket bookTicket)
	{
		bookTicket.setBookingId(id);
		bookTicketService.updateBookingById(id,bookTicket);
		logger.info("Update booking controller called....");
		return new ResponseEntity<String>("Booking Details Updated Successfully....",HttpStatus.OK);
	}
	
	/*********************************************************************************************************************************/
	
	
	
	/*
	 *This method will fetch booking details from database using bookingId. 
	 *@GetMapping mapping HTTP GET requests onto specific handler methods.
	 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI).
	 *
	 * */
	
	@GetMapping("/getBooking/{bookingId}")
	public ResponseEntity<?> getBookingById(@Valid @PathVariable int bookingId)
	{
		Optional<?> bookTicket = bookTicketService.getAllBookings(bookingId);
		
		if(bookTicket == null)
		{
			throw new RecordNotFoundException("No record found....");
		}
		logger.info("Getbooking by id booking controller called....");
		return new ResponseEntity<>(bookTicket,HttpStatus.OK);
	}
	
	/*********************************************************************************************************************************/
	

	/*
	 * This method is used to delete booking from database using bookingId.
	 * @DeleteMapping maps HTTP DELETE requests onto specific handler methods.
	 * 
	 * */
	
	@DeleteMapping("/deleteBooking/{bookingId}")
	public ResponseEntity<String> deleteBookingById(@Valid @PathVariable int bookingId, Offerdto odto)
	{
		bookTicketService.deleteBookingById(bookingId);
		if(bookingId <= 0 ) {
			throw new RecordNotFoundException("No reccord found....");
		}
		logger.info("Delete booking controller called....");
		senderService.sendEmail("ashiphs@cybage.com","Passenger Name: " + odto.getTotalPassenger() + "\nTotal Amount: " + odto.getTotalAmount() + "\nFlight ID: " + odto.getFlightId(),"Ticket has been cancelled Successful");
		return new ResponseEntity<String>("Customer Deleted Succesfully.....",HttpStatus.OK);
	}
	
	/*****************************************************************************************************************************/
	
	
	/*
	 * This method will get all tickets by user Id.
	 * @RequestParam a Spring annotation used to bind a web request parameter to a method parameter.
	 * */
	
		@GetMapping("/getAllTicketsByUserId")
		public ResponseEntity<List<BookTicket>> getTicketByUserId(@RequestParam int userId){
			logger.info("searchFlight Controller is called********");
			List<BookTicket> ticketList = bookTicketService.getTicketByUserId(userId);
			return new ResponseEntity<List<BookTicket>>(ticketList,HttpStatus.OK);
		}
	

	
}
