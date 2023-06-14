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
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Activity name can not be empty")
    private String activityName;

    @NotBlank(message = "CreatedAt can not be blank")
    private Timestamp createdAt;

    @NotBlank(message = "UpdatedAt can not be blank")
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "activities")
    Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private Set<SubActivity> activities = new HashSet<>();
}
