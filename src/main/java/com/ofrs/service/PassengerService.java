package com.ofrs.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofrs.model.Passenger;
import com.ofrs.repository.PassegerRepository;

@Service
@Transactional
public class PassengerService {

	@Autowired
	PassegerRepository passegerRepository;

	public void addPassenger(Passenger passenger) {
		
		passegerRepository.save(passenger);
		
	}
	
	
	
}
