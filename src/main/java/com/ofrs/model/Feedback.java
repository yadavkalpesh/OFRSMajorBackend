package com.ofrs.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @ManyToOne used to create the many-to-one relationship between the Parent and Child entities.
 * @NotNull specifies that the field should not be left blank
 * 
 * */

@Entity
@Table(name = "feedback")
//@JsonIgnoreProperties(value = {"user","handler"},allowSetters = true)
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feebackId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private RegisterUser user;
	
	@NotNull
	@NotEmpty(message = "Feedback should not empty")
	private String feedbackDetails;
	
	@NotNull
	private int rating;
	
	
	public Feedback() {}


	public Feedback(int feebackId, RegisterUser user, String feedbackDetails, int rating) {
		super();
		this.feebackId = feebackId;
		this.user = user;
		this.feedbackDetails = feedbackDetails;
		this.rating = rating;
	}


	public Feedback(RegisterUser user, String feedbackDetails, int rating) {
		super();
		this.user = user;
		this.feedbackDetails = feedbackDetails;
		this.rating = rating;
	}


	public int getFeebackId() {
		return feebackId;
	}


	public void setFeebackId(int feebackId) {
		this.feebackId = feebackId;
	}


	public RegisterUser getUser() {
		return user;
	}


	public void setUser(RegisterUser user) {
		this.user = user;
	}


	public String getFeedbackDetails() {
		return feedbackDetails;
	}


	public void setFeedbackDetails(String feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	@Override
	public String toString() {
		return "Feedback [feebackId=" + feebackId + ", user=" + user + ", feedbackDetails=" + feedbackDetails
				+ ", rating=" + rating + "]";
	}
	
	
}