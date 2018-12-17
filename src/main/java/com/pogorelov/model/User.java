package com.pogorelov.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class User {
    private int id;
    private String login;
    private String password;
    private ROLE role;

    public enum ROLE {
        CLIENT, DRIVER, UNKNOWN
    }
}
