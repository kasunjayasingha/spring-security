package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Loans;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: LoanService
 * @Created on: 6/17/2025 at 10:29 AM
 */
public interface LoanService {

    List<Loans> getLoansDetails(long id);
}
