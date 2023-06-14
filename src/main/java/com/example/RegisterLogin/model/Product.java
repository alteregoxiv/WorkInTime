package com.example.RegisterLogin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Product name can not be empty")
    private String productName;

    @NotBlank(message = "CreatedAt can not be blank")
    private Timestamp createdAt;

    @NotBlank(message = "UpdatedAt can not be blank")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    Set<User> users = new HashSet<>();
}
