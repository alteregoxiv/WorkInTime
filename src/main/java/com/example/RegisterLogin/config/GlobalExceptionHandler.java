package com.example.RegisterLogin.config;

import com.example.RegisterLogin.dto.response.FilterResponseDTO;
import com.example.RegisterLogin.exceptions.IncorrectPasswordException;
import com.example.RegisterLogin.exceptions.InvalidActionException;
import com.example.RegisterLogin.exceptions.InvalidStatusException;
import com.example.RegisterLogin.exceptions.UserNotFoundException;
import com.example.RegisterLogin.util.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleNotFoundException(UserNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.NOT_FOUND.value(), "User does not exist", errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleInvalidStatusException(InvalidStatusException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "User does not exist", errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "User does not exist", errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidActionException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleInvalidActionException(InvalidActionException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "User does not exist", errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
