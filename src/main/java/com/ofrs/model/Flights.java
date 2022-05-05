package com.ofrs.model;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @ManyToOne used to create the many-to-one relationship between the Parent and Child entities.
 * @Size is used to set the customized condition for the field.
 *  
 * */

@Entity
@Table(name = "flights")
public class Flights {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long flightId;
	
	@Size(min = 2,message = "Flight Name should have atleast 2 characters")
	private String flightName;
	
	@Size(min = 2,message = "Source Name should have atleast 2 characters")
	private String source;
	
	@Size(min = 2,message = "Destination Name should have atleast 2 characters")
	private String destination;
	
	@NotEmpty(message = "Departure Date cannot be empty.")
	private String departureDate;
	
	@NotEmpty(message = "Departure Time cannot be empty.")
	private String departureTime;
	
	@NotEmpty(message = "Stops cannot be empty.")
	private String stops;
	
	@Min(value = 1,message = "Total Seats should be greater than 0")
	private int totalSeats;
	
	@Min(value = 1,message = "Base Price should be greater than 0")
	private double basePrice;
	
	
	@OneToMany(mappedBy = "flight",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<BookTicket> ticket;
	
	
	public Flights() {}
	


	public Flights(long flightId, String flightName, String source, String destination, String departureDate,
			String departureTime, String stops, int totalSeats, double basePrice, List<BookTicket> ticket) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.stops = stops;
		this.totalSeats = totalSeats;
		this.basePrice = basePrice;
		this.ticket = ticket;
	}



	public Flights(String flightName, String source, String destination, String departureDate, String departureTime,
			String stops, int totalSeats, double basePrice, List<BookTicket> ticket) {
		super();
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.stops = stops;
		this.totalSeats = totalSeats;
		this.basePrice = basePrice;
		this.ticket = ticket;
	}

	


	public Flights(long flightId, String flightName, String source, String destination, String departureDate,
			String departureTime, String stops, int totalSeats, double basePrice) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.stops = stops;
		this.totalSeats = totalSeats;
		this.basePrice = basePrice;
	}



	public List<BookTicket> getTicket() {
		return ticket;
	}


	public void setTicket(List<BookTicket> ticket) {
		this.ticket = ticket;
	}



	public long getFlightId() {
		return flightId;
	}



	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}



	public String getFlightName() {
		return flightName;
	}



	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public String getDepartureDate() {
		return departureDate;
	}



	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}



	public String getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}



	public String getStops() {
		return stops;
	}



	public void setStops(String stops) {
		this.stops = stops;
	}



	public int getTotalSeats() {
		return totalSeats;
	}



	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}



	public double getBasePrice() {
		return basePrice;
	}



	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}



	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", flightName=" + flightName + ", source=" + source + ", destination="
				+ destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", stops="
				+ stops + ", totalSeats=" + totalSeats + ", basePrice=" + basePrice + "]";
	}
	
	
	
}
