package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Cards;
import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.CardsRepository;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import com.kasunjay.springsecurity.service.CardsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: CardServiceImpl
 * @Created on: 6/17/2025 at 10:29 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<Cards> getCardsDetails(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isEmpty()) {
            log.warn("Customer with email {} not found", email);
            return null;
        }
        return cardsRepository.findByCustomerId(customerOptional.get().getId());
    }
}
