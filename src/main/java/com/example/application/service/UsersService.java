package com.example.application.service;

import com.example.application.model.Users;

import java.util.List;


public interface UsersService {
    void saveUsers(Users users);
    List<Users> getAllUsers();
    Users findByEmailAndPassword(String email, String password);
    Users findByEmail(String email);
}
