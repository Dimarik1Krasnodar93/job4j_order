package ru.job4j.job4j_order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.job4j_order.model.Card;
import ru.job4j.job4j_order.service.CustomerService;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/buyCard")
    public Card buyCard(@RequestBody Card card) {
        customerService.buyCard(card);
        return card;
    }
}
