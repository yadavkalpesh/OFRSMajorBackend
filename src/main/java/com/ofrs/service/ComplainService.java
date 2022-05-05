package com.ofrs.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofrs.model.BookTicket;
import com.ofrs.model.Complain;
import com.ofrs.repository.ComplainRepository;
import ch.qos.logback.classic.Logger;

@Service
public class ComplainService {

	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(ComplainService.class);
	
	@Autowired
	private ComplainRepository complainRepository;

	public List<Complain> getAllComplain() {
		logger.info("Get all complain method service called.....");
		return complainRepository.findAll();
	}

	public void addComplain(Complain complain) {
		logger.info("Add complain method called......");
		complainRepository.save(complain);
		
	}

	public Optional<?> getAllComplain(int complainId) {
		
		Optional<?> complain = complainRepository.findById(complainId);
		logger.info("Complain getById service method called....");
		return complain;
	}
	
	
	public void deleteComplainById(int complainId) {
		
		logger.info("Complain deleteById method is called.....");
		complainRepository.deleteById(complainId);
	}

	public Complain updateComplainById(int complainId, Complain complain) {
		
		logger.info("Update complain service method called.....");
		return complainRepository.save(complain);
	}
	
	//get all list by userId
	public List<Complain> getComplainByUserId(int userId){
		logger.info("getComplainByUserId Service is called********");
		return complainRepository.getComplainByUserId(userId);
	}
	

}
