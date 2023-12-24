package com.amadeus.FlightSearchApi.Service;

import com.amadeus.FlightSearchApi.Entity.User;
import com.amadeus.FlightSearchApi.Exception.UserNotFoundException;
import com.amadeus.FlightSearchApi.Repository.UserRepository;
import com.amadeus.FlightSearchApi.Security.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + username));
          return UserDetailsImpl.create(user);
        }

}

