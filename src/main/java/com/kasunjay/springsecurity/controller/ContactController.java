package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Contact;
import com.kasunjay.springsecurity.service.CardsService;
import com.kasunjay.springsecurity.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: ContactController
 * @Created on: 5/27/2025 at 9:42 PM
 */
@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contact")
    public Contact saveContactInquiryDetails (@RequestBody Contact contact) {
        return contactService.saveContactInquiryDetails(contact);
    }
}
