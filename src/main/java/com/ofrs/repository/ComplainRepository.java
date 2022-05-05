package com.ofrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ofrs.model.BookTicket;
import com.ofrs.model.Complain;

@Repository
public interface ComplainRepository extends JpaRepository<Complain, Integer>
{

	//Get List of Complain list By User ID
	@Query(value = "SELECT * FROM complain where user_id =?;",nativeQuery = true)
	public List<Complain> getComplainByUserId(int userId);
	
}
