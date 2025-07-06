package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.model.LoginRequestDTO;
import com.kasunjay.springsecurity.model.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: UserService
 * @Created on: 6/5/2025 at 8:42 PM
 */
public interface UserService {
    Customer getUserDetails(String username);
}
