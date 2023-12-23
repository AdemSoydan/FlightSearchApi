package com.amadeus.FlightSearchApi.Service;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Repository.AirportRepository;
import com.amadeus.FlightSearchApi.Request.AirportRequest;
import com.amadeus.FlightSearchApi.Response.AirportResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportService {
    private AirportRepository airportRepository;

    public Optional<Airport> getAirportById(int id){
        return airportRepository.findById(id);
    }
    public Airport saveAirport(AirportRequest airportRequest){
        return airportRepository.save(new Airport(airportRequest.getCity()));
    }

    public List<AirportResponse> findAll(){
        return airportRepository.findAll().stream().map(airport ->
                new AirportResponse(airport.getId(),airport.getCity())).collect(Collectors.toList());
    }
}
