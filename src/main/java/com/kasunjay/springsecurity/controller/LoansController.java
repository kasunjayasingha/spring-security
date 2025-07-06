package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Loans;
import com.kasunjay.springsecurity.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: LoansController
 * @Created on: 5/27/2025 at 9:44 PM
 */
@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanService loanService;

    @GetMapping("/myLoans")
    @PreAuthorize("hasAuthority('VIEWLOANS') or hasRole('USER')")
    public List<Loans> getLoansDetails (@RequestParam String email) {
        return loanService.getLoansDetails(email);
    }
}
