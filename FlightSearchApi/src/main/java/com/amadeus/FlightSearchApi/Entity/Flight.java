package com.amadeus.FlightSearchApi.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne()
    @JoinColumn(name = "depature_airport")
    Airport departureAirport;
    @OneToOne()
    @JoinColumn(name = "arrival_airport")
    Airport arrivalAirport;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;

}
