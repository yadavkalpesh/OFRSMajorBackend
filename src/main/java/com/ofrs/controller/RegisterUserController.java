package com.ofrs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ofrs.exception.InputNotProvidedException;
import com.ofrs.exception.InvalidInputProvidedException;
import com.ofrs.exception.RecordAlreadyPresentException;
import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Passenger;
import com.ofrs.model.RegisterUser;
import com.ofrs.model.UserPassengerDTO;
import com.ofrs.service.EmailSenderService;
import com.ofrs.service.PassengerService;
import com.ofrs.service.RegisterUserService;
import com.ofrs.util.JwtUtil;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("http://localhost:4200/")


/*
 * 
 * 
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * @ClassName RegisterUserController
 * @RegisterUserController.java holds all the CRUD operation related to booking ticket
 * 
 * 
 * */



public class RegisterUserController {

	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(RegisterUserController.class);
	
	/*
	 * @Autowired enables dependency injection implicitly
	 * @Logger is to generate the log file report for each method being called
	 * @CrossOrigin enables cross-origin resource sharing only for this specific method.
	 * @RestController enables REST web services
	 * 
	 * */

	@Autowired
	RegisterUserService registerUserService;
	
	@Autowired
	EmailSenderService senderService;
	
	@Autowired
	PassengerService passengerService;

	@PostMapping("/")
	public String welcome() {
		return "Welcome to OFRS !!";
	}

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
	
//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody RegisterUser authRequest) throws InvalidInputProvidedException {
//		System.out.println(authRequest);
//
//		if (authRequest == null) {
//			throw new InputNotProvidedException("Please enter username/password");
//		}
//
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword())
//			);
//		} catch (Exception ex) {
//			throw new InvalidInputProvidedException("Inavalid username/password");
//		}
//		return jwtUtil.generateToken(authRequest.getUserEmail());
//	}

	
	//
	@PostMapping("/authenticate")
	public ResponseEntity<?> loginUser(@RequestBody RegisterUser user) {
		RegisterUser userObj = registerUserService.findByuserEmail(user.getUserEmail());
		 
		if (userObj != null) {
				System.out.println("Login controller called ");
			if (userObj.getPassword().equals(user.getPassword())) {
				if (userObj.getAttempts() > 2) {
					return new ResponseEntity<>("Account locked", HttpStatus.UNAUTHORIZED);
				}
				userObj.setAttempts(0);
				registerUserService.addUser(userObj);

				int otpCheck = senderService.sendOTP(user.getUserEmail());
				return new ResponseEntity<>(otpCheck, HttpStatus.OK);
			} else {
				if (userObj.getAttempts() > 2) {
					return new ResponseEntity<>("Account locked", HttpStatus.UNAUTHORIZED);
				}
				userObj.setAttempts(userObj.getAttempts() + 1);
				registerUserService.addUser(userObj);
				return new ResponseEntity<>("Password is wrong ", HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<>("User not found " ,HttpStatus.UNAUTHORIZED);
	}
	
	

/****************************************************************************************************************************/
	
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
	
	
	@PostMapping("/authenticate/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUser registerUser, HttpServletRequest request) {

		if (registerUser != null) {

//			String password = registerUser.getPassword();
//			System.out.println(password);
//			String encryptedPwd = passwordEncoder.encode(password);
//			registerUser.setPassword(encryptedPwd);
			registerUserService.registerUser(registerUser, getSiteURL(request));
			logger.info("************************Register Successfully*****************************");
			return new ResponseEntity<>("User Added Succesfully", HttpStatus.CREATED);
		}
		logger.error("***************************Registration Failed*******************************");
		return new ResponseEntity<>("Please Enter All the Information ", HttpStatus.NOT_ACCEPTABLE);

	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	
	/*********************************************************************************************************************************/
	
	/*
	 *This method will fetch all bookings from database
	 *@GetMapping mapping HTTP GET requests onto specific handler methods.
	 *@throw used to declare exceptions that can occur during the execution of a program.
	 *@RecordNotFoundException will show exception message if the condition is true.
	 * 
	 * */
	
	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (registerUserService.verify(code)) {
			System.out.println("user verified");
			return "verify_success";
		} else {
			System.out.println("user not verified");
			return "verify_fail";
		}
	}

	/*******************************************************************************************************************/
	/*
	 *This method will fetch all bookings from database
	 *@GetMapping mapping HTTP GET requests onto specific handler methods.
	 *@throw used to declare exceptions that can occur during the execution of a program.
	 *@RecordNotFoundException will show exception message if the condition is true.
	 * 
	 * */

	@GetMapping("/getAllUser")
	public ResponseEntity<List<RegisterUser>> getAllUser() {
		List<RegisterUser> userList = new ArrayList<RegisterUser>();
		userList = registerUserService.getAllUser();

		if (userList.isEmpty()) {
			throw new RecordNotFoundException("No users Found");
		}

		logger.info("***Get all user controller is called***");
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	/*************************************************************************************************************************/

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<RegisterUser> getUser(@PathVariable int userId) {
		RegisterUser user = registerUserService.getUser(userId);

		if (user == null) {
			throw new RecordNotFoundException("No user Found");
		}

		logger.info("***Get user controller is called***");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/****************************************************************************************************************************/
	
	/*
	 * This method is used to delete booking from database using bookingId.
	 * @DeleteMapping maps HTTP DELETE requests onto specific handler methods.
	 * 
	 * */
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {

		if (userId <= 0) {
			throw new InvalidInputProvidedException("Invalid Input Provided");
		}

		registerUserService.deleteUser(userId);
		logger.info("***Delete user controller is called***");
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	
	/*******************************************************************************************************************************/
	
	/*
	 *This method will update booking in database with bookingId. 
	 *@PathVariable annotation is used to extract the value from the Uniform Resource Identifier(URI). 
	 *@PutMapping handle the HTTP POST requests matched with given URI expression.
	 * 
	 * */

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<RegisterUser> updateUser(@PathVariable int userId, @Valid @RequestBody RegisterUser user) {

		if (user == null) {
			throw new InputNotProvidedException("User is empty");
		}
		if (user.getUserId() <= 0) {
			throw new InvalidInputProvidedException("Invalid Input Provided");
		}

		RegisterUser user_new = registerUserService.updateUser(userId, user);
		logger.info("***Update user controller is called***");
		return new ResponseEntity<>(user_new, HttpStatus.OK);
	}

	/*******************************************************************************************************************************/
	
	@GetMapping("/authenticate/findByuserEmail/{userEmail}")
	public ResponseEntity<RegisterUser> findByuserEmail(@Valid @PathVariable String userEmail) {
		if (userEmail == null) {
			throw new InputNotProvidedException("User email is empty");
		}
		RegisterUser user = registerUserService.findByuserEmail(userEmail);
		logger.info("***Find by email user controller is called***");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/*******************************************************************************************************************************/
	
	@GetMapping("/findByUserName/{userName}")
	public ResponseEntity<?> findByUserName(@Valid @PathVariable String userName) {
		if (userName == null) {
			throw new InputNotProvidedException("Username is empty");
		}
		RegisterUser user = registerUserService.findByUserName(userName);
		logger.info("***Find by username controller is called***\n" + user + "\n");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/*******************************************************************************************************************************/
	
	@PostMapping("/addPassenger")
	public ResponseEntity<?> addPassenger(@RequestBody UserPassengerDTO upDto){
		Passenger passenger = UserPassengerDTO.toEntityPassenger(upDto);
		passengerService.addPassenger(passenger);
		
		return ResponseEntity.ok(passenger);
	}
	
	//get all locked account users
		@GetMapping("/getAllLockedUsers")
		public ResponseEntity<?> getAllLockedUsers(){
			List<RegisterUser> list = registerUserService.getAllLockedUsers();
			if(!list.isEmpty()) {
			return new ResponseEntity<>(list,HttpStatus.OK);
			}
			else {
		   throw new RecordNotFoundException("no Locked accounts");	
			}}
		
		//unlock user by admin
		@GetMapping("/unlockUser/{id}")
		public ResponseEntity<?> unlockusers(@PathVariable int id){
			registerUserService.unlockuserAccount(id);
			return new ResponseEntity<>("user is unlocked",HttpStatus.OK);
		}
	
}