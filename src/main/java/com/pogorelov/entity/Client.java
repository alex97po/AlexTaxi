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
public class Client {
    private Long id;
    private String name;
    private BigDecimal moneySpent;
    private ClientAuth clientAuth;

}
