package com.example.RegisterLogin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequestDTO {
    private String taskName;
    private long productId;
    private long activityId;
}
