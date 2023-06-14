package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Product;
import com.example.RegisterLogin.model.User;
import com.example.RegisterLogin.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductDAO extends JpaRepository<UserProduct, Long> {

    @Query("SELECT up.product FROM UserProduct up WHERE up.user = :user")
    List<Product> findProductsByUserId(@Param("user") User user);
}
