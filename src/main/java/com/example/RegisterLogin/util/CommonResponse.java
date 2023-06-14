package com.example.RegisterLogin.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private int status;
    private String message;
    private T data;
    private String error;

//    map success
    public CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

//    map failure
    public CommonResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }
}
