package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Accounts;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: AccountService
 * @Created on: 6/17/2025 at 10:25 AM
 */
public interface AccountService {

    Accounts getAccountDetails(long id);
}
