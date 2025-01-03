package com.example.application.service;

import com.example.application.model.Users;
import com.example.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveUsers(Users users) {
        Optional<Users> user = usersRepository.findByEmail(users.getEmail());
        if (user != null) {
            throw new RuntimeException("Email already exists");
        }else {
            users.setId(UUID.randomUUID().toString());
            usersRepository.save(users);
        }

    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    @Override
    public Users findByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }
}
