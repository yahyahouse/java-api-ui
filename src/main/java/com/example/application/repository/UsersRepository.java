package com.example.application.repository;

import com.example.application.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<Users> getAllUsers();

    Users findByEmailAndPassword(String email, String password);
    Users findByEmail(String email);

}
