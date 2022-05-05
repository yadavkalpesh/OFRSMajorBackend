package com.ofrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ofrs.model.Flights;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long>
{

	//searching flight according to source, destination and departure_date for home page filter
	@Query(value = "SELECT * FROM flights where source=?1 and destination=?2 and departure_date=?3",nativeQuery = true)
	public List<Flights> searchFlight(String source,String destination,String departure_date);
	
	public List<Flights> findByFlightName(String flightName);
}
