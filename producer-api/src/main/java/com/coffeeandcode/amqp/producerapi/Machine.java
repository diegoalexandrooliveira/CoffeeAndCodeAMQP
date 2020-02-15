package com.coffeeandcode.amqp.producerapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Machine {
    private String brand;
    private String model;
    private String fabricationYear;
}
