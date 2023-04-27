package ru.job4j.job4j_order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.repository.OrderRepository;

@AllArgsConstructor
@Service
public class OrderService {
    OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Status getStatus(int orderId) {
        return orderRepository.getStatus(orderId);
    }
}
