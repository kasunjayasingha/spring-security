package com.kasunjay.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: NoticesController
 * @Created on: 5/27/2025 at 9:44 PM
 */
@RestController
public class NoticesController {

    @GetMapping("/notices")
    public  String getNotices () {
        return "Here are the notices details from the DB";
    }
}
