package com.ofrs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ofrs.model.RegisterUser;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer> {
	RegisterUser findByUserEmail(String userEmail);
	
	RegisterUser findByUserName(String userName);
	@Query("SELECT u FROM RegisterUser u WHERE u.verificationCode = ?1")
    public RegisterUser findByVerificationCode(String code);
	
	@Query("SELECT u FROM RegisterUser u WHERE u.attempts = 3")
	public List<RegisterUser> findAllLockedusers();
	
	@Modifying
	@Transactional
	@Query("update RegisterUser u set u.attempts=0 where u.userId =?1")
	public void unlockUserAccount(int id);
	
}
