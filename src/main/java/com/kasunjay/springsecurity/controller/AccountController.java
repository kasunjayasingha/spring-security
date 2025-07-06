package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Accounts;
import com.kasunjay.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: AccountController
 * @Created on: 5/27/2025 at 9:43 PM
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails (@RequestParam String email) {
        return accountService.getAccountDetails(email);
    }
}
