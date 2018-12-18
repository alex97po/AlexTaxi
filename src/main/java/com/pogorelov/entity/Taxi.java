package com.pogorelov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Taxi {
    private long id;
    private String carType;
    private String stateNumber;
    private String driverName;
}
