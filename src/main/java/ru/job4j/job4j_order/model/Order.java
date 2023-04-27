package ru.job4j.job4j_order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private int id;
    private String name;
    private Status status;
}
