package com.kasunjay.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: AccountController
 * @Created on: 5/27/2025 at 9:43 PM
 */
@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public  String getAccountDetails () {
        return "Here are the account details from the DB";
    }
}
