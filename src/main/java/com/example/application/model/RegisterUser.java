package com.example.application.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUser {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String message;
}
