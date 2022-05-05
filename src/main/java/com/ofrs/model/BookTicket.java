package com.ofrs.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/*
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * @BookingTicket.java is a POJO class.
 *  
 * */

/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @JsonIgnoreProperties marks a field in a POJO to be ignored by Jackson during serialization and deserialization.
 * 	to avoid loop formation.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @FetchType.LAZY tells Hibernate to only fetch the related entities from the database when you use the relationship.
 * @JsonProperty used to map property names with JSON keys during serialization and deserialization.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @ManyToOne used to create the many-to-one relationship between the Parent and Child entities.
 *  
 * */


@Entity
@Table(name = "bookTicket")
@JsonIgnoreProperties(value = {"flight","user","offers","handler","hibernateLazyInitializer"},allowSetters = true)
public class BookTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "flightId")
	private Flights flight;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "userId")
	private RegisterUser user;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offerId")
	private Offer offers;

	
	
	private int totalPassenger;
	private double totalAmount;
	
	
	public BookTicket() {
	}
	


	public BookTicket(int bookingId, int totalPassenger, double totalAmount) {
		super();
		this.bookingId = bookingId;
		this.totalPassenger = totalPassenger;
		this.totalAmount = totalAmount;
	}



	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public Flights getFlight() {
		return flight;
	}


	public void setFlight(Flights flight) {
		this.flight = flight;
	}


	public RegisterUser getUser() {
		return user;
	}


	public void setUser(RegisterUser user) {
		this.user = user;
	}


	public Offer getOffers() {
		return offers;
	}


	public void setOffers(Offer offers) {
		this.offers = offers;
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



	@Override
	public String toString() {
		return "BookTicket [bookingId=" + bookingId + ", flight=" + flight + ", user=" + user + ", offers=" + offers
				+ ", totalPassenger=" + totalPassenger + ", totalAmount=" + totalAmount + "]";
	}

	

}
