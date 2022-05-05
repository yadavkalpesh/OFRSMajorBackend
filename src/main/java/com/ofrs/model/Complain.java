package com.ofrs.model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @ManyToOne used to create the many-to-one relationship between the Parent and Child entities.
 *  
 * */

@Entity
@Table(name = "complain")
public class Complain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int complainId;
	@Size(min = 2, message = "Complain details should have atleast 2 characters.")
	private String complainDetails;
	private String complainStatus;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private RegisterUser user;
	

	public Complain() {
		// TODO Auto-generated constructor stub
	}


	public Complain(int complainId, String complainDetails, String complainStatus, RegisterUser user) {
		
		this.complainId = complainId;
		this.complainDetails = complainDetails;
		this.complainStatus = complainStatus;
		this.user = user;
	}



	public Complain(String complainDetails, String complainStatus, RegisterUser user) {
		
		this.complainDetails = complainDetails;
		this.complainStatus = complainStatus;
		this.user = user;
	}



	public Complain(String complainDetails,String complainStatus) {
		this.complainDetails = complainDetails;
		this.complainStatus = complainStatus;
	}


	public RegisterUser getUser() {
		return user;
	}


	public void setUser(RegisterUser user) {
		this.user = user;
	}

	public int getComplainId() {
		return complainId;
	}


	public void setComplainId(int complainId) {
		this.complainId = complainId;
	}




	public String getComplainDetails() {
		return complainDetails;
	}


	public void setComplainDetails(String complainDetails) {
		this.complainDetails = complainDetails;
	}


	public String getComplainStatus() {
		return complainStatus;
	}


	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}




	@Override
	public String toString() {
		return "Complain [complainId=" + complainId + ", complainDetails=" + complainDetails + ", complainStatus="
				+ complainStatus + ", user=" + user + "]";
	}


	
	

}
