package com.ofrs.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @JsonIgnoreProperties marks a field in a POJO to be ignored by Jackson during serialization and deserialization.
 * 	to avoid loop formation.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @OneToMany used to create the one-to-many relationship between the Parent and Child entities.
 *  
 * */

@Entity
@Table(name = "offer")
@JsonIgnoreProperties(value = {"ticket","handler","hibernateLazyInitializer"},allowSetters = true)
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int offerId;
	
	@Size(min = 2,message = "Offer Name should have atleast 2 characters")
	private String offerName;
	
	@Size(min = 2,message = "Offer Code should have atleast 2 characters")
	private String offerCode;
	
	@Min(value = 1,message = "Offer Amount should be greater than 0")
	private double offerAmount;
	
	
	@OneToMany(mappedBy = "offers",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<BookTicket>bookTickets;
	
	public Offer() {
		this.bookTickets=new ArrayList<>();
	}
	
	

	
	public Offer(int offerId, @Size(min = 2, message = "Offer Name should have atleast 2 characters") String offerName,
			@Size(min = 2, message = "Offer Code should have atleast 2 characters") String offerCode,
			@Min(value = 1, message = "Offer Amount should be greater than 0") double offerAmount) {
		this.offerId = offerId;
		this.offerName = offerName;
		this.offerCode = offerCode;
		this.offerAmount = offerAmount;
	}



	public List<BookTicket> getBookTickets() {
		return bookTickets;
	}


	public void setBookTickets(List<BookTicket> bookTickets) {
		this.bookTickets = bookTickets;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerName=" + offerName + ", offerCode=" + offerCode + ", offerAmount="
				+ offerAmount + "]";
	}
	
	

}
