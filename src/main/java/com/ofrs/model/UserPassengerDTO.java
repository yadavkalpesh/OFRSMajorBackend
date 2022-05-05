package com.ofrs.model;

import org.springframework.beans.BeanUtils;

public class UserPassengerDTO {
	
	private int userId;
	private int passengerId;
	
	private String passengerName;
	private String gender;
	private String contactNumber;
	
	public UserPassengerDTO() {
	}

	public UserPassengerDTO(int userId, int passengerId, String passengerName, String gender, String contactNumber) {
		this.userId = userId;
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.gender = gender;
		this.contactNumber = contactNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "UserPassengerDTO [userId=" + userId + ", passengerId=" + passengerId + ", passengerName="
				+ passengerName + ", gender=" + gender + ", contactNumber=" + contactNumber + "]";
	}

	public static Passenger toEntityPassenger(UserPassengerDTO upDto) {
		Passenger entity= new Passenger();
		BeanUtils.copyProperties(upDto, entity);
		
		RegisterUser user = new RegisterUser();
		user.setUserId(upDto.getUserId());
		entity.setUser(user);
		
		return entity;
	}
	
	
	
}
