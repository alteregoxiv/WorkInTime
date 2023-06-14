package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    Product findById(long productId);
}
