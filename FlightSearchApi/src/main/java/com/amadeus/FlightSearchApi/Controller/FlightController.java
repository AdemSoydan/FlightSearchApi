package com.amadeus.FlightSearchApi.Controller;

import com.amadeus.FlightSearchApi.Entity.Flight;
import com.amadeus.FlightSearchApi.Repository.FlightRepository;
import com.amadeus.FlightSearchApi.Request.FlightRequest;
import com.amadeus.FlightSearchApi.Response.FlightResponse;
import com.amadeus.FlightSearchApi.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping()
    public Flight createFlight(@RequestBody FlightRequest request){
        return flightService.createFlight(request);
    }

    @GetMapping()
    public ResponseEntity<List<FlightResponse>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }
}
