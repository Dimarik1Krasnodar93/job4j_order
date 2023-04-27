package ru.job4j.job4j_order.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.job4j_order.model.Card;


public interface CardRepository {
    Card save(Card card);
}
