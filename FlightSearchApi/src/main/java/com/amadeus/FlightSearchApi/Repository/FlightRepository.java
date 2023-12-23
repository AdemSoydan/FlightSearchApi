package com.amadeus.FlightSearchApi.Repository;

import com.amadeus.FlightSearchApi.Entity.Flight;
import com.amadeus.FlightSearchApi.Response.FlightResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    @Query("SELECT f FROM Flight f " +
            "JOIN f.departureAirport a1 " +
            "JOIN f.arrivalAirport a2 " +
            "WHERE (f.departureTime > :startOfTheDay OR f.departureTime < :endOfTheDay)" +
            "AND a1.city = :departureCity " +
            "AND a2.city = :arrivalCity")
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTime(
            @Param("departureCity") String departureCity,
            @Param("arrivalCity") String arrivalCity,
            @Param("startOfTheDay") LocalDateTime startOfTheDay,
            @Param("endOfTheDay") LocalDateTime endOfTheDay);
}
