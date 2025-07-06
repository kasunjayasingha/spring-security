package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Cards;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: CardService
 * @Created on: 6/17/2025 at 10:28 AM
 */
public interface CardsService {

    List<Cards> getCardsDetails(String email);
}
