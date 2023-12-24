package com.amadeus.FlightSearchApi.Response;

import lombok.Data;

@Data
public class AuthResponse {
    String message;
    int userId;
    String accessToken;
}
