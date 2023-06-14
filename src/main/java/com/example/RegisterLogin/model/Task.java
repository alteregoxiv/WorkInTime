package com.example.RegisterLogin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

// uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "activity_id"})
@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Task name can not be blank")
    private String taskName;

    @NotBlank(message = "CreatedAt can not be null")
    private Timestamp createdAt;

    @NotBlank(message = "UpdatedAt can not be null")
    private Timestamp updatedAt;

    private Integer estimatedTime;

    private Timestamp startedAt;

    private Timestamp finishedAt;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer totalTime;

    @NotBlank(message = "Must belong to a Product")
    private long productId;

    @NotBlank(message = "Must belong to an Activity")
    private long activityId;

    @ManyToMany(mappedBy = "tasks")
    Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();
}
