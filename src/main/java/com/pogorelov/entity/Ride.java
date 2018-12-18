package com.pogorelov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ride {
    private long id;
    private BigDecimal cost;
    private double distance;
    private Client client;
    private Taxi taxi;
}
