package com.ofrs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ofrs.model.Offer;
import com.ofrs.repository.FlightRepository;
import com.ofrs.repository.OfferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class OfferServiceTest {

	@Autowired
	private OfferService offerService;
	
	@MockBean
	private OfferRepository offerRepository;
	
	@Test
	void testAddOffer() {
		Offer offer = new Offer(1,"Super25", "Super25", 25);
		
		Mockito.when(offerRepository.save(offer)).thenReturn(offer);

		assertThat(offer.getOfferId() == 1);

		assertThat(offer != null);
	}

	@Test
	void testGetAllOffer() {
		Offer offer1 = new Offer(1,"Super25", "Super25", 25);
		Offer offer2 = new Offer(2,"Super50", "Super25", 25);
		
		List<Offer> offerList = new ArrayList<>();
		offerList.add(offer1);
		offerList.add(offer2);
		
		Mockito.when(offerService.getAllOffer()).thenReturn(offerList);
		assertThat(offerService.getAllOffer()).isEqualTo(offerList);
		
	}

	@Test
	void testDeleteOffer() {
		Offer offer1 = new Offer(1,"Super50", "Super25", 25);
		
		assertThat(offer1.getOfferId()>0);
		Mockito.when(offerRepository.getById(1)).thenReturn(offer1);
		
	}

	@Test
	void testUpdateOffer() {
		Offer offer1 = new Offer(1,"Super25", "Super25", 25);
		
		assertThat(offer1.getOfferId()>0);
		Mockito.when(offerRepository.getById(1)).thenReturn(offer1);
		
		offer1.setOfferName("flat54");
		Mockito.when(offerRepository.save(offer1)).thenReturn(offer1);
		
		assertThat(offerService.updateOffer(1, offer1)).isEqualTo(offer1);
	}

//	@Test
//	@Ignore
//	void testFindByOfferName() {
//		fail("Not yet implemented");
//	}

}
