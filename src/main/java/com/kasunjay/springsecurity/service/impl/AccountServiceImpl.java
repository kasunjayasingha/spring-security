package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Accounts;
import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.AccountsRepository;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import com.kasunjay.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: AccountServiceImpl
 * @Created on: 6/17/2025 at 10:25 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Accounts getAccountDetails(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isEmpty()) {
            log.warn("Customer with email {} not found", email);
            return null;
        }
        return accountsRepository.findByCustomerId(customerOptional.get().getId());
    }
}
