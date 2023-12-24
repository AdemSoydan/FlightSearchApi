package com.amadeus.FlightSearchApi.Controller;

import com.amadeus.FlightSearchApi.Entity.User;
import com.amadeus.FlightSearchApi.Request.UserRequest;
import com.amadeus.FlightSearchApi.Response.AuthResponse;
import com.amadeus.FlightSearchApi.Security.JwtUtil;
import com.amadeus.FlightSearchApi.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtUtil jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateToken(auth);
        User user = userService.getUserByUserName(loginRequest.getUserName()).get();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(jwtToken);
        authResponse.setUserId(user.getId());
        authResponse.setMessage("User successfully login.");
        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getUserByUserName(registerRequest.getUserName()).isPresent()) {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveUser(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUserName(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateToken(auth);

        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken(jwtToken);
        authResponse.setUserId(user.getId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/welcome")
    public String hello(){
        return "Hello";
    }
}
