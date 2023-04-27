package ru.job4j.job4j_order.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.job4j_order.model.Card;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CardRepositoryMem implements CardRepository{
    private List<Card> cards = new ArrayList<>();
    private static int count = 0;

    @Override
    public Card save(Card card) {
        card.setId(count);
        cards.add(card);
        return card;
    }
}
