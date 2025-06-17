package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Contact;
import com.kasunjay.springsecurity.repository.ContactRepository;
import com.kasunjay.springsecurity.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: ContactServiceImpl
 * @Created on: 6/17/2025 at 11:06 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public Contact saveContactInquiryDetails(Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
