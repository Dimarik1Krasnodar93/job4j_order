package ru.job4j.job4j_order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;
    OrderRepository orderRepository;
    Param param;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaTemplate.send("job4j_orders2", objectMapper.writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return savedOrder;
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

    public Order save(Order order) {
        var savedOrder = orderRepository.save(order);
        kafkaTemplate.send("job4j_orders", savedOrder);
        return savedOrder;
    }
}
