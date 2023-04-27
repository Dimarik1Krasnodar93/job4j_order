package ru.job4j.job4j_order.model;

import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
}
