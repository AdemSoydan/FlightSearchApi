package com.amadeus.FlightSearchApi.Repository;

import com.amadeus.FlightSearchApi.Entity.Flight;
import com.amadeus.FlightSearchApi.Response.FlightResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

}
