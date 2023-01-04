package com.roager.controllers;

import com.roager.models.Cards;
import com.roager.repositories.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam int id) {
        return cardsRepository.findByCustomerId(id);
    }
}
