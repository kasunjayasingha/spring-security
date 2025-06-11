package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            customerRepository.save(customer);
            return ResponseEntity.created(null).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }
}
