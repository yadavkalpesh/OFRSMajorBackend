package com.ofrs.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofrs.exception.RecordNotFoundException;
import com.ofrs.model.Flights;
import com.ofrs.model.Offer;
import com.ofrs.repository.OfferRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OfferService {

	//Logger logger = org.slf4j.LoggerFactory.getLogger(OfferService.class);

	@Autowired
	private OfferRepository offerrepo;

	public void addOffer(Offer offer) {
		log.info("addOffer Service is called********");
		offerrepo.save(offer);
	}

	public List<Offer> getAllOffer() {
		log.info("getAllOffer Service is called********");
		return offerrepo.findAll();
	}

	public void deleteOffer(int id) {
		log.info("deleteOffer Service is called********");
		offerrepo.deleteById(id);
	}

	public Offer updateOffer(int id, Offer offer) {
		log.info("updateOffer Service is called********");
		return offerrepo.save(offer);
	}

	// search Offer according to Offer Name
	public List<Offer> findByOfferName(String OfferName) {
		log.info("findByOfferName Service is called********");
		return offerrepo.findByOfferName(OfferName);
	}


	public Offer getOfferById(int id) {
		log.info("getOfferById Service is called********");
		return offerrepo.findById(id).orElseThrow(()-> new RecordNotFoundException("Record Not Found for id :"+id));
	}

	
}
