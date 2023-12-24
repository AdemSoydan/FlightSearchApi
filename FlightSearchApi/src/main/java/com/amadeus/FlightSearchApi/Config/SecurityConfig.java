package com.amadeus.FlightSearchApi.Config;

import com.amadeus.FlightSearchApi.Security.AuthenticationFilter;
import com.amadeus.FlightSearchApi.Security.JwtAuthenticationEntryPoint;
import com.amadeus.FlightSearchApi.Service.UserDetailServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {"/auth/**",
            "/auth/register/**", "/auth/login/**"};
    private final AuthenticationFilter jwtAuthFilter;

    private final UserDetailServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint handler;


    public SecurityConfig(AuthenticationFilter jwtAuthFilter, UserDetailServiceImpl userService, PasswordEncoder passwordEncoder,
    JwtAuthenticationEntryPoint handler
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.handler = handler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(handler).and()
                .authorizeHttpRequests(x ->
                        x.requestMatchers("/auth/welcome/**").permitAll()
                                .requestMatchers("/auth/register/**").permitAll()
                                .requestMatchers("/auth/login/**").permitAll()
                )

                .authorizeHttpRequests(x ->
                        x.requestMatchers("/airport").authenticated()
                                .requestMatchers("/Flight").authenticated()
                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }
}
