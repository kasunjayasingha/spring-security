package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.AccountTransactions;
import com.kasunjay.springsecurity.repository.AccountTransactionsRepository;
import com.kasunjay.springsecurity.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<AccountTransactions> getBalanceDetails(long id) {

        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactions != null) {
            return accountTransactions;
        } else {
            return null;
        }
    }
}
