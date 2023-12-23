package com.amadeus.FlightSearchApi.Controller;

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
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest request){
        return new ResponseEntity<>(flightService.createFlight(request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<FlightResponse>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    public FlightResponse getFlightById(@PathVariable int flightId) {
        return flightService.getFlightById(flightId);
    }

    @PutMapping("/{flightId}")
    public FlightResponse updateFlight(@PathVariable int flightId, @RequestBody FlightRequest request) {
        return flightService.updateFlight(flightId, request);
    }

    @DeleteMapping("/{flightId}")
    public FlightResponse deleteFlight(@PathVariable int flightId) {
        return flightService.deleteFlightById(flightId);
    }
}
