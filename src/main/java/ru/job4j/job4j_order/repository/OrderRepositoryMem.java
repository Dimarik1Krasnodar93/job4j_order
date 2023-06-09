package ru.job4j.job4j_order.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Repository
public class OrderRepositoryMem implements OrderRepository {
    private List<Order> orders = new ArrayList<>();
    private static int count = 0;
    @Override
    public Order save(Order order) {
        if (order.getId() == 0) {
            order.setId(++count);
            orders.add(order);
        }
        return order;
    }

    @Override
    public Status getStatus(int orderId) {
        return findById(orderId).getStatus();
    }

    @Override
    public Order findById(int orderId) {
        return orders.stream().filter(i -> i.getId() == orderId).findFirst().orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
