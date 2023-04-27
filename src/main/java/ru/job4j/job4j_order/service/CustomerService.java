package ru.job4j.job4j_order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Card;
import ru.job4j.job4j_order.repository.CardRepository;

@AllArgsConstructor
@Service
public class CustomerService {
    CardRepository cardRepository;
    public void buyCard(Card card) {
        cardRepository.save(card);
    }
}
