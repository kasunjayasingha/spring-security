package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Cards;
import com.kasunjay.springsecurity.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: CardsController
 * @Created on: 5/27/2025 at 9:44 PM
 */
@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsService cardsService;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails (@RequestParam long id) {
        return cardsService.getCardsDetails(id);
    }
}
