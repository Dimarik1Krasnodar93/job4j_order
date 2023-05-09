package ru.job4j.job4j_order.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.job4j_order.model.Card;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;

import java.util.List;

@Repository
public interface OrderRepository {
    Order save(Order order);
    Status getStatus(int orderId);
    Order findById(int orderId);
    List<Order> findAll();

}
