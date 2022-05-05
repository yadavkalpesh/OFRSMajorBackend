package com.ofrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofrs.model.Flights;
import com.ofrs.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>
{
	
	public List<Offer> findByOfferName(String offerName);

}
