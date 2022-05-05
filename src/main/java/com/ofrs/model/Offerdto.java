package com.ofrs.model;

import org.springframework.beans.BeanUtils;

public class Offerdto {
	

	private int offerId;
	private int userId;
	private int bookingId;
	private long flightId;
	private int passengerId;
	private String passengerName;
	private String gender;
	private String contactNumber;
	private int totalPassenger;
	private double totalAmount;
	
	public Offerdto() {
		
	}

	

	public Offerdto(int offerId, int userId, int bookingId, long flightId, int passengerId, String passengerName,
			String gender, String contactNumber, int totalPassenger,double totalAmount) {
		super();
		this.offerId = offerId;
		this.userId = userId;
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.totalPassenger = totalPassenger;
		this.totalAmount = totalAmount;
	}
	
	
	
	public int getOfferId() {
		return offerId;
	}
	

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getTotalPassenger() {
		return totalPassenger;
	}

	public void setTotalPassenger(int totalPassenger) {
		this.totalPassenger = totalPassenger;
	}


	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public static BookTicket toOfferModel(Offerdto odto)
	{
		BookTicket entity= new BookTicket();
		BeanUtils.copyProperties(odto, entity);
		
		Flights flight = new Flights();
		flight.setFlightId(odto.getFlightId());
		entity.setFlight(flight);
		
		
		Offer offer = new Offer();
		offer.setOfferId(odto.getOfferId());
		entity.setOffers(offer);
		
		Passenger passanger = new Passenger();
		passanger.setContactNumber(odto.getContactNumber());
		passanger.setGender(odto.getGender());
		passanger.setPassengerName(odto.getPassengerName());
		
		
		RegisterUser ruser = new RegisterUser();
		ruser.setUserId(odto.getUserId());
		
		ruser.addPassenger(passanger);
		entity.setUser(ruser);	

		
		
		return entity;
	}



	public static Offerdto fromEntity(BookTicket bookTicket) {
		
		Offerdto dto = new Offerdto();
		BeanUtils.copyProperties(bookTicket, dto);
		dto.setFlightId(bookTicket.getFlight().getFlightId());
		return dto;
		
	}

	
}
