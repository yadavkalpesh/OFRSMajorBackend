package com.ofrs.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/*
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * @BlockedUser.java is a POJO class.
 *  
 * @Entity  specifies that the class is an entity and is mapped to a database table. 
 * @Table allows you to specify the details of the table that will be used to persist the entity in the database.
 * @Id specifies the primary key of an entity.
 * @OneToOne marks the relationship between two entities with one-to-one multiplicity.
 * 
 * */

@Entity
@Table(name = "blockedUser")
public class BlockedUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private RegisterUser user;

	public BlockedUser(int id, RegisterUser user) {
		super();
		this.id = id;
		this.user = user;
	}

	public BlockedUser(RegisterUser user) {
		super();
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RegisterUser getUser() {
		return user;
	}

	public void setUser(RegisterUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BlockedUser [id=" + id + ", user=" + user + "]";
	}
	
	

}
