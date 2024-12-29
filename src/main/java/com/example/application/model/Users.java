package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Users {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String message;

}
