package com.ofrs.service;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ofrs.model.BookTicket;
import com.ofrs.repository.BookingTicketRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingTicketServiceTest {

	@Autowired
	private BookingTicketService bookTicketService;
	
	
	@MockBean
	private BookingTicketRepository bookingTicketRepository;
	
	
	@Test
	public void testAddBooking() {
		BookTicket bookTicket = new BookTicket(1,3,10500.00);
		Mockito.when(bookingTicketRepository.save(bookTicket)).thenReturn(bookTicket);
		assertThat(bookTicket.getBookingId()==1);
		//assertThat(bookTicket!=null);
	}

	@Test
	public void testGetAllBookings() {
		BookTicket bookTicket1 = new BookTicket(1, 3,10500.00);
		BookTicket bookTicket2 = new BookTicket(2, 3, 8500.00);
		
		List<BookTicket> bookTicketList = new ArrayList<>();
		bookTicketList.add(bookTicket1);
		bookTicketList.add(bookTicket2);
		
		Mockito.when(bookTicketService.getAllBookings()).thenReturn(bookTicketList);
		assertThat(bookTicketService.getAllBookings()).isEqualTo(bookTicketList);
	}

	@Test
	public void testUpdateBookingById() {
		BookTicket bookTicket1 = new BookTicket(1,3,10500.00);
		
		assertThat(bookTicket1.getBookingId()>0);
		Mockito.when(bookingTicketRepository.save(bookTicket1)).thenReturn(bookTicket1);
		
		//bookTicket1.setPassangerName1("Ankit");
		Mockito.when(bookingTicketRepository.save(bookTicket1)).thenReturn(bookTicket1);
		
		assertThat(bookTicketService.updateBookingById(2, bookTicket1)).isEqualTo(bookTicket1);
	}

	

	@Test
	public void testDeleteBookingById() {
		BookTicket bookTicket = new BookTicket(1, 3,10500.00);
		
		assertThat(bookTicket.getBookingId()>0);
		Mockito.when(bookingTicketRepository.getById(1)).thenReturn(bookTicket);
	}

}
