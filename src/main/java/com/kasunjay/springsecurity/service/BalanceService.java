package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.AccountTransactions;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: BalanceService
 * @Created on: 6/17/2025 at 10:27 AM
 */
public interface BalanceService {

    List<AccountTransactions> getBalanceDetails(String email);
}
