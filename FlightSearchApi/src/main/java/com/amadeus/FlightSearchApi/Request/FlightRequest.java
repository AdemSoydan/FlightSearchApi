package com.amadeus.FlightSearchApi.Request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class FlightRequest {
    int departureAirportId;
    int arrivalAirportId;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    double price;
}
