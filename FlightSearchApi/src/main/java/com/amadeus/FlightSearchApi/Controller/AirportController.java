package com.amadeus.FlightSearchApi.Controller;

import com.amadeus.FlightSearchApi.Entity.Airport;
import com.amadeus.FlightSearchApi.Repository.AirportRepository;
import com.amadeus.FlightSearchApi.Request.AirportRequest;
import com.amadeus.FlightSearchApi.Request.FlightRequest;
import com.amadeus.FlightSearchApi.Response.AirportResponse;
import com.amadeus.FlightSearchApi.Response.FlightResponse;
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
    public AirportResponse createAirport(@RequestBody AirportRequest airportRequest){
        return airportService.saveAirport(airportRequest);
    }

    @GetMapping
    public List<AirportResponse> getAllAirports(){
        return airportService.findAll();
    }

    @GetMapping("/{airportId}")
    public AirportResponse getAirportById(@PathVariable int airportId) {
        return airportService.getAirportById(airportId);
    }

    @PutMapping("/{airportId}")
    public AirportResponse updateAirport(@PathVariable int airportId, @RequestBody AirportRequest request) {
        return airportService.updateAirport(airportId, request);
    }

    @DeleteMapping("/{airportId}")
    public AirportResponse deleteFlight(@PathVariable int airportId) {
        return airportService.deleteAirportById(airportId);
    }
}
