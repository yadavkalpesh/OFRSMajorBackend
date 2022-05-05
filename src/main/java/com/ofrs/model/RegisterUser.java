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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;



/*
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * 	to avoid loop formation.
 * @Id specifies the primary key of an entity.
 * @GeneratedValue provides the specification of generation strategies for the primary keys values.
 * @JoinColumn used to specify a column for joining an entity association or element collection. 
 * @OneToMany used to create the one-to-many relationship between the Parent and Child entities.
 *  
 * */



@Entity
@Table(name = "register_user")
@JsonIgnoreProperties(value = {"complain","feedback","blockedUser","ticket","handler","hibernateLazyInitializer"},allowSetters = true)
public class RegisterUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@NotNull
	@NotEmpty
	@Size(min=2, message="Name should have atleast two characters")
	private String userName;
	
	@Email
	private String userEmail;
	
	
	private String imagePath;
	
	@NotEmpty
	@NotNull
	@Size(min=10, message="Number should have atleast ten digits")
	private String contactNumber;
	
	@NotNull
	@Size(min=8, message="Passport should have atleast eight characters")
	private String password;
	
	
	private String role;
	
	private int attempts;
	
	private String verificationCode;
	
	private boolean isEnabled;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Feedback> feedback ;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Complain> complain;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private BlockedUser blockedUser;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<BookTicket> ticket;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Passenger> passengerList;
	
	
	
	
	public RegisterUser() {
		this.feedback=new ArrayList<>();
		this.complain=new ArrayList<>();
		this.ticket=new ArrayList<>();
		this.passengerList=new ArrayList<>();
	}



	public RegisterUser(int userId, String userName, String userEmail, String imagePath, String contactNumber,
			String password, List<Feedback> feedback, List<Complain> complain, BlockedUser blockedUser,
			List<BookTicket> ticket) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.imagePath = imagePath;
		this.contactNumber = contactNumber;
		this.password = password;
		this.feedback = feedback;
		this.complain = complain;
		this.blockedUser = blockedUser;
		this.ticket = ticket;
	}



	public RegisterUser(String userName, String userEmail, String imagePath, String contactNumber, String password,
			List<Feedback> feedback, List<Complain> complain, BlockedUser blockedUser, List<BookTicket> ticket) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.imagePath = imagePath;
		this.contactNumber = contactNumber;
		this.password = password;
		this.feedback = feedback;
		this.complain = complain;
		this.blockedUser = blockedUser;
		this.ticket = ticket;
	}


	public RegisterUser(int userId,
			@NotNull @NotEmpty @Size(min = 2, message = "Name should have atleast two characters") String userName,
			@Email String userEmail, String imagePath, String role, int attempts, String verificationCode,
			boolean isEnabled,
			@NotEmpty @NotNull @Size(min = 10, message = "Number should have atleast ten digits") String contactNumber,
			@NotNull @Size(min = 8, message = "Passport should have atleast eight characters") String password,
			List<Feedback> feedback, List<Complain> complain, BlockedUser blockedUser, List<BookTicket> ticket) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.imagePath = imagePath;
		this.role = role;
		this.attempts = attempts;
		this.verificationCode = verificationCode;
		this.isEnabled = isEnabled;
		this.contactNumber = contactNumber;
		this.password = password;
		this.feedback = feedback;
		this.complain = complain;
		this.blockedUser = blockedUser;
		this.ticket = ticket;
	}


	

	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getAttempts() {
		return attempts;
	}



	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}



	public String getVerificationCode() {
		return verificationCode;
	}



	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}



	public boolean isEnabled() {
		return isEnabled;
	}



	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}



	public List<BookTicket> getTicket() {
		return ticket;
	}



	public void setTicket(List<BookTicket> ticket) {
		this.ticket = ticket;
	}



	public List<Feedback> getFeedback() {
		return feedback;
	}



	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}



	public List<Complain> getComplain() {
		return complain;
	}



	public void setComplain(List<Complain> complain) {
		this.complain = complain;
	}



	public BlockedUser getBlockedUser() {
		return blockedUser;
	}



	public void setBlockedUser(BlockedUser blockedUser) {
		this.blockedUser = blockedUser;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Passenger> getPassengerList() {
		return passengerList;
	}



	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}



	public void addPassenger(Passenger passenger) {
		passengerList.add(passenger);
		passenger.setUser(this);
	}



	@Override
	public String toString() {
		return "RegisterUser [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", imagePath="
				+ imagePath + ", contactNumber=" + contactNumber + ", password=" + password + ", role=" + role
				+ ", attempts=" + attempts + ", verificationCode=" + verificationCode + ", isEnabled=" + isEnabled
				+ ", feedback=" + feedback + ", complain=" + complain + ", blockedUser=" + blockedUser + ", ticket="
				+ ticket + ", passengerList=" + passengerList + "]";
	}


	
	
	
	
}
