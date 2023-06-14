package com.example.RegisterLogin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterResponseDTO {
    private List<Map<Long, String>> activities;
    private List<Map<Long, String>> tasks;
}
