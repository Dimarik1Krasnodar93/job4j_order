package ru.job4j.job4j_order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.dto.Dish;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.service.OrderService;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("status/{orderId}")
    public Status getStatus(@PathVariable int orderId) {
        return orderService.getStatus(orderId);
    }

    @GetMapping("/{id}")
    public Dish getOrderInfo(@PathVariable("id") int id) {
        return orderService.getOrderInfo(id).getBody();
    }

}
