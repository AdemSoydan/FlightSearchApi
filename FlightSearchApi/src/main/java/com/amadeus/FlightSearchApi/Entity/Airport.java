package com.amadeus.FlightSearchApi.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String city;

   @OneToMany(mappedBy = "departureAirport", cascade={CascadeType.PERSIST, CascadeType.MERGE,
           CascadeType.DETACH, CascadeType.REFRESH})
   private List<Flight> outGoingFlights;

    @OneToMany(mappedBy = "arrivalAirport", cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Flight> inGoingFlights;
}
