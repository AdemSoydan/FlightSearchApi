package com.amadeus.FlightSearchApi.Service;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Entity.Flight;
import com.amadeus.FlightSearchApi.Exception.AirportNotFoundException;
import com.amadeus.FlightSearchApi.Repository.FlightRepository;
import com.amadeus.FlightSearchApi.Request.FlightRequest;
import com.amadeus.FlightSearchApi.Response.FlightResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;
    private AirportService airportService;

    public Flight createFlight(FlightRequest request){
       Optional<Airport> departureAirport =  airportService.getAirportById(request.getDepartureAirportId());
       Optional<Airport> arrivalAirport =  airportService.getAirportById(request.getArrivalAirportId());
       if(departureAirport.isEmpty() || arrivalAirport.isEmpty()){
           throw new AirportNotFoundException("Departure or arrival airport not found");
       }

       Flight createdFlight =  Flight.builder()
               .departureAirport(departureAirport.get())
               .arrivalAirport(arrivalAirport.get())
               .arrivalTime(request.getArrivalTime())
               .departureTime(request.getDepartureTime())
               .price(request.getPrice())
               .build();

       return  flightRepository.save(createdFlight);
    }

    public List<FlightResponse> getAllFlights(){
       return flightRepository.findAll().stream().map(this::flightToFlightResponse).collect(Collectors.toList());
    }

    private FlightResponse flightToFlightResponse(Flight flight){
        return new FlightResponse(flight);
    }
}
