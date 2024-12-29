package com.example.application.service;

import com.example.application.model.Users;
import com.example.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveUsers(Users users) {
        users.setId(UUID.randomUUID().toString());
        usersRepository.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }
}
