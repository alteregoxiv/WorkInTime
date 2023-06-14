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
@Table(name = "sub_activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "SubActivity name can not be empty")
    private String subActivityName;

    @NotBlank(message = "CreatedAt can not be blank")
    private Timestamp createdAt;

    @NotBlank(message = "UpdatedAt can not be blank")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
