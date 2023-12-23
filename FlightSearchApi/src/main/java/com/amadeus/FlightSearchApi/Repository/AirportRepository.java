package com.amadeus.FlightSearchApi.Repository;

import com.amadeus.FlightSearchApi.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Integer> {
}
