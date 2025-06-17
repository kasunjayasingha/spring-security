package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Accounts;
import com.kasunjay.springsecurity.repository.AccountsRepository;
import com.kasunjay.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    @Override
    public Accounts getAccountDetails(long id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }
}
