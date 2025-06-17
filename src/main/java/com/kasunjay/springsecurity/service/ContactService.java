package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Contact;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: ContactService
 * @Created on: 6/17/2025 at 11:06 AM
 */
public interface ContactService {

    Contact saveContactInquiryDetails(Contact contact);
}
