package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Task;
import com.example.RegisterLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDAO extends JpaRepository<Task, Long> {
    
    List<Task> findByUsers(User user);
    Task findById(long taskId);
}
