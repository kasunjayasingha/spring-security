package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.AccountTransactions;
import com.kasunjay.springsecurity.model.Customer;
import com.kasunjay.springsecurity.repository.AccountTransactionsRepository;
import com.kasunjay.springsecurity.repository.CustomerRepository;
import com.kasunjay.springsecurity.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: BalanceServiceImpl
 * @Created on: 6/17/2025 at 10:27 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<AccountTransactions> getBalanceDetails(String email) {

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isEmpty()) {
            log.warn("Customer with email {} not found", email);
            return null;
        }
        return accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(customerOptional.get().getId());
    }
}
