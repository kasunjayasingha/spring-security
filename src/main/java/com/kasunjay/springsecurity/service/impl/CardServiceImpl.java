package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Cards;
import com.kasunjay.springsecurity.repository.CardsRepository;
import com.kasunjay.springsecurity.service.CardsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: CardServiceImpl
 * @Created on: 6/17/2025 at 10:29 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;

    @Override
    public List<Cards> getCardsDetails(long id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null ) {
            return cards;
        }else {
            return null;
        }
    }
}
