package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.AccountTransactions;
import com.kasunjay.springsecurity.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: BalanceController
 * @Created on: 5/27/2025 at 9:43 PM
 */
@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails (@RequestParam long id) {
        return balanceService.getBalanceDetails(id);
    }
}
