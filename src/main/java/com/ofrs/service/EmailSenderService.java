package com.ofrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ofrs.model.BookTicket;
import com.ofrs.model.Flights;
import com.ofrs.model.RegisterUser;

@Service
public class EmailSenderService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toEmail,
//                                String body,
//                                String subject) {
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("Trng1@evolvingsols.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//        System.out.println("Mail Send...");
//    }

	
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	RegisterUserService registerUserService;
	
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("Trng1@evolvingsols.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }
	
//	otp method()
	public int sendOTP(String email) {
		int otp = 0;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("Trng1@evolvingsols.com");
			message.setTo(email);
			otp = (int) (Math.floor(Math.random() * 1000000));
			message.setText("OTP for login is " + otp);
			message.setSubject("OTP for Login");
			System.out.println("mail() before sent check on console");

			mailSender.send(message);
			System.out.println("mail() after sent check on console");
		} catch (MailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return otp;
	}
	
//	send email after booking
	public void sendEmail(BookTicket customer) throws MailException {
       RegisterUser user = registerUserService.getUser(customer.getUser().getUserId());
       Flights flight=flightService.getFlightById(customer.getFlight().getFlightId());
		SimpleMailMessage mail = new SimpleMailMessage();
		String email = user.getUserEmail();
		mail.setFrom("Trng1@evolvingsols.com");
		mail.setTo(email);
		mail.setSubject("Ticket booking status");
		mail.setText("Dear  " + user.getUserName()
				+ "\n This email is to inform you that your ticket has been booked on : "
				+ "\n for flight details are as follows \n (1) departure date : " 
				+ "\n (2) Timeslot : " + flight.getDepartureTime()
				+ "\n Please be present on time for your flight.\n Thank you");
		mailSender.send(mail);
		}   
	
	
//	send mail after cancelling ticket
	public void sendEmailforDeletion(BookTicket customer) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		String email = customer.getUser().getUserEmail();
		mail.setFrom("Trng1@evolvingsols.com");
		mail.setTo(email);
		mail.setSubject("Ticket booking status");
		mail.setText("Dear  "+customer.getUser().getUserName()+"\n This email is to inform you that your ticket has been cancelled.\n\n Thank you");
		mailSender.send(mail);
	}
	

}
