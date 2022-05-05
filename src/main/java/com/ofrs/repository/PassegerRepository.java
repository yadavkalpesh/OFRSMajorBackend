package com.ofrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofrs.model.Passenger;

public interface PassegerRepository extends JpaRepository<Passenger, Integer>{

}
