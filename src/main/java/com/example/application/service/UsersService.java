package com.example.application.service;

import com.example.application.model.Users;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsersService {
    void saveUsers(Users users);
    List<Users> getAllUsers();
    Users findByEmailAndPassword(String email, String password);
}
