package ru.job4j.job4j_order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.Param;
import ru.job4j.job4j_order.dto.Dish;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.Map;
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
            kafkaTemplate.send("job4j_orders_1", objectMapper.writeValueAsString(savedOrder));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return savedOrder;
    }

    @KafkaListener(topics = "cooked_order")
    public void receiveOrder(String orderStr) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> map = gson.fromJson(orderStr, Map.class);
        Order order = orderRepository.findById(((Double) map.get("id")).intValue());
        if (order != null && "CANCEL".equals(map.get("status"))) {
            order.setCancelled(true);
            orderRepository.save(order);
        }
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
