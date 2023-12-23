package com.amadeus.FlightSearchApi.Request;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Response.AirportResponse;
import lombok.Data;

@Data
public class AirportRequest {
    private String city;
}
