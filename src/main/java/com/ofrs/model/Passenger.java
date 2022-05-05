package com.ofrs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * 	to avoid loop formation.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @ManyToOne used to create the many-to-one relationship between the Parent and Child entities.
 *  
 * */

@Entity
public class Passenger {

	@Id
	@GeneratedValue(generator = "passenegr_seq",strategy=GenerationType.IDENTITY)
	private int passengerId;
	private String passengerName;
	private String gender;
	private String contactNumber;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "userId")
	private RegisterUser user;
		
	
	
	
	public Passenger() {
		
	}

	public Passenger(int passengerId, String passengerName, String gender, String contactNumber
			) {
		
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		
	}

	public Passenger(String passengerName, String gender, String contactNumber) {
		this.passengerName = passengerName;
		this.gender = gender;
		this.contactNumber = contactNumber;
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

	public RegisterUser getUser() {
		return user;
	}

	public void setUser(RegisterUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", gender=" + gender
				+ ", contactNumber=" + contactNumber + ", user=" + user + "]";
	}

	

	
	
}
