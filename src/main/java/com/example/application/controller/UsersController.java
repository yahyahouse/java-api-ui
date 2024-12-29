package com.example.application.controller;

import com.example.application.model.Users;
import com.example.application.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public void saveUsers(@RequestBody Users users) {
        usersService.saveUsers(users);
    }
}
