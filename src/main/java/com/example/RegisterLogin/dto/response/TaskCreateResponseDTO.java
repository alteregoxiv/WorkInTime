package com.example.RegisterLogin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateResponseDTO {
    private long taskId;
    private String taskName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
