package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.constants.ApplicationConstants;
import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.model.LoginRequestDTO;
import com.kasunjay.springsecurity.model.LoginResponseDTO;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: UserServiceImpl
 * @Created on: 6/5/2025 at 8:43 PM
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.kasunjay.springsecurity.service.UserService {

    private final CustomerRepository customerRepository;
    private final Environment env;

    @Override
    public Customer getUserDetails(String username) {
        try {
            Optional<Customer> customer = customerRepository.findByEmail(username);
            if (customer.isPresent()) {
                return customer.get();
            } else {
                return null; // or throw an exception if preferred
            }
        } catch (Exception e) {
            // Log the exception (optional)
            // log.error("Error fetching user details for {}: {}", username, e.getMessage());
            return null; // or throw an exception if preferred
        }
    }
}
