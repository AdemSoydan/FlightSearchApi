package com.amadeus.FlightSearchApi.Controller;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Repository.AirportRepository;
import com.amadeus.FlightSearchApi.Request.AirportRequest;
import com.amadeus.FlightSearchApi.Response.AirportResponse;
import com.amadeus.FlightSearchApi.Service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping(consumes = "application/json")
    public Airport createAirport(@RequestBody AirportRequest airport){
        return airportService.saveAirport(airport);
    }

    @GetMapping
    public List<AirportResponse> getAllAirports(){
        return airportService.findAll();
    }
}
