package ru.job4j.job4j_order.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.Param;
import ru.job4j.job4j_order.dto.Dish;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.Properties;

@AllArgsConstructor
@Service
public class OrderService {
    OrderRepository orderRepository;
    Param param;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Status getStatus(int orderId) {
        return orderRepository.getStatus(orderId);
    }

    public ResponseEntity<Dish> getOrderInfo(int id) {
        Properties properties = param.getProperties();
        String url = String.format("%s%s/%d",
                properties.getProperty("server.url"),
                properties.getProperty("server.getDish"),
                id);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        return  restTemplate.exchange(url,
                HttpMethod.GET, request, Dish.class);
    }
}
