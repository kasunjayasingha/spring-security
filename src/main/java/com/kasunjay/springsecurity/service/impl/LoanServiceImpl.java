package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Loans;
import com.kasunjay.springsecurity.repository.LoanRepository;
import com.kasunjay.springsecurity.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: LoanServiceImpl
 * @Created on: 6/17/2025 at 10:29 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public List<Loans> getLoansDetails(long id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }
}
