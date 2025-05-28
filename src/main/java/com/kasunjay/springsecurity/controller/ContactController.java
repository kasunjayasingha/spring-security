package com.kasunjay.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: ContactController
 * @Created on: 5/27/2025 at 9:42 PM
 */
@RestController
public class ContactController {

    @GetMapping("/contact")
    public  String saveContactInquiryDetails () {
        return "Inquiry details are saved to the DB";
    }
}
