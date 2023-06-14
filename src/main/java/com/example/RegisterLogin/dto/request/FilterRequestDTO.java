package com.example.RegisterLogin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequestDTO {
    private List<Long> product;
    private List<Long> activity;
    private String status;
}
