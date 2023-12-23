package com.amadeus.FlightSearchApi.Response;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Entity.Flight;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponse {
    int id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    double price;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.departureAirport = flight.getDepartureAirport();
        this.arrivalAirport = flight.getArrivalAirport();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.price = flight.getPrice();
    }
}
