package ru.job4j.job4j_order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private int id;
    private int dishId;
    private String name;
    private Status status;
    private boolean cancelled;
}
