package com.amadeus.FlightSearchApi.Exception;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(String message) {
        super(message);
    }
}
