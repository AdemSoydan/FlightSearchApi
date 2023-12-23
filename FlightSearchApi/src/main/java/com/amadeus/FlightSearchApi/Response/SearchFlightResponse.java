package com.amadeus.FlightSearchApi.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightResponse {
    List<FlightResponse> departureFlights;
    List<FlightResponse> returnFlights;
}
