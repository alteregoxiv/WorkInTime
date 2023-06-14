package com.example.RegisterLogin.dto.response;

import com.example.RegisterLogin.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingTasksResponseDTO {
    private List<Map<Long, String>> pendingTasks;
}
