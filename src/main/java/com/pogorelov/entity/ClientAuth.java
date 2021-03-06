package com.pogorelov.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientAuth {
    private long id;
    private String login;
    private String password;

    public ClientAuth(String login,String password){
        this.login = login;
        this.password = password;
    }


}
