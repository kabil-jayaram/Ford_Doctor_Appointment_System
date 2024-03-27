package com.example.springsecurity.entity;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsDto {
    private int id;
    private String userName;
    private String roles;
    private String authToken;

    public UserDetailsDto(int id, String userName, String roles, String authToken) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
        this.authToken = authToken;
    }
}
