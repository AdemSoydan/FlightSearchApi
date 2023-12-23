package com.amadeus.FlightSearchApi.Response;

import lombok.Data;

@Data
public class AirportResponse {
    private int id;
    private String city;

    public AirportResponse(int id, String city) {
        this.id = id;
        this.city = city;
    }
}
