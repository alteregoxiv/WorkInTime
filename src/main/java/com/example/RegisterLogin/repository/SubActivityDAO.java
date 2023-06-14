package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.SubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubActivityDAO extends JpaRepository<SubActivity, Long> {
//    List<SubActivity> findAll();
    List<SubActivity> findByActivityIdIn(List<Long> activityId);
}
