package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDAO extends JpaRepository<Activity, Long> {
//    List<Activity> findAll();
    List<Activity> findByProductId(Long productId);

    @Query("SELECT a FROM Activity a WHERE a.product.id IN :productIds")
    List<Activity> findActivitiesByProductIds(@Param("productIds") List<Long> productIds);
}
