package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// multiple ways to query using jpa repository
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findById(int id);
}
