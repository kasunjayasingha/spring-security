package com.kasunjay.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: BalanceController
 * @Created on: 5/27/2025 at 9:43 PM
 */
@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public  String getBalanceDetails () {
        return "Here are the balance details from the DB";
    }
}
