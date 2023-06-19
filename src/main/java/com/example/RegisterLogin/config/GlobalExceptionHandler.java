package com.example.RegisterLogin.config;

import com.example.RegisterLogin.exceptions.UserNotFoundException;
import com.example.RegisterLogin.util.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    public ResponseEntity<CommonResponse<Map<String, List<String>>>> handleNotFoundException(UserNotFoundException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
//        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }
}
