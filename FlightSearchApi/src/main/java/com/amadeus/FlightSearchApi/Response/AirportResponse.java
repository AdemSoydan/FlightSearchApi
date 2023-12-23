package com.amadeus.FlightSearchApi.Response;

import com.amadeus.FlightSearchApi.Entity.Airport;
import lombok.Data;

@Data
public class AirportResponse {
    private int id;
    private String city;

    public AirportResponse(Airport airport) {
        this.id = airport.getId();
        this.city = airport.getCity();
    }



}
