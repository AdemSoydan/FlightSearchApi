package com.amadeus.FlightSearchApi.Service;

import com.amadeus.FlightSearchApi.Entity.User;
import com.amadeus.FlightSearchApi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
