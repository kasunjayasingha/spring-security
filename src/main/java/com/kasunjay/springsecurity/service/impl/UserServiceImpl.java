package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

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
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(Customer customer) {
        try {
            // Encode the password before saving
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            customerRepository.save(customer);
            return ResponseEntity.created(null).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

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
