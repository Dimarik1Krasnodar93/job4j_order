package ru.job4j.job4j_order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.service.OrderService;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/create")
    private Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("status/{orderId}")
    private Status getStatus(@PathVariable int orderId) {
        return orderService.getStatus(orderId);
    }
}
