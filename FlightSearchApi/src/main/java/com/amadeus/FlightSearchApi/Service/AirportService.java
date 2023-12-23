package com.amadeus.FlightSearchApi.Service;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Exception.AirportNotFoundException;
import com.amadeus.FlightSearchApi.Repository.AirportRepository;
import com.amadeus.FlightSearchApi.Request.AirportRequest;
import com.amadeus.FlightSearchApi.Request.FlightRequest;
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

    public AirportResponse getAirportById(int id){
        Optional<Airport> dbAirport =  airportRepository.findById(id);
        if(dbAirport.isEmpty())
            throw new AirportNotFoundException("The airport is not found by the id: " + id);
        return new AirportResponse(dbAirport.get());
    }
    public AirportResponse saveAirport(AirportRequest airportRequest){
        Airport savedAirport =  airportRepository.save(new Airport(airportRequest.getCity().toUpperCase()));
        return new AirportResponse(savedAirport);
    }

    public List<AirportResponse> findAll(){
        return airportRepository.findAll().stream().map(AirportResponse::new).collect(Collectors.toList());
    }

    public AirportResponse deleteAirportById(int id){
       Optional<Airport> dbAirport = airportRepository.findById(id);
       if(dbAirport.isEmpty())
           throw new AirportNotFoundException("The Airport that want to be deleted is not found");
       airportRepository.deleteById(id);
       return new AirportResponse(dbAirport.get());
    }


    public AirportResponse updateAirport(int airportId, AirportRequest request) {
        Optional<Airport> dbAirport = airportRepository.findById(airportId);
        if(dbAirport.isEmpty())
            throw new AirportNotFoundException("The Airport that is want to be updated is not found");


        Airport airport = dbAirport.get();
        airport.setCity(request.getCity().toUpperCase());

        return new AirportResponse(airport);
    }
}
