package com.example.RegisterLogin.controller;

import com.example.RegisterLogin.util.AuthorizationUtil;
import com.example.RegisterLogin.util.CommonResponse;
import com.example.RegisterLogin.dto.request.LogInRequestDTO;
import com.example.RegisterLogin.dto.response.LoginResponseDTO;
import com.example.RegisterLogin.dto.response.RegistrationResponseDTO;
import com.example.RegisterLogin.model.User;
import com.example.RegisterLogin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.RegisterLogin.dto.request.RegistrationRequestDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    AuthorizationUtil authorizationUtil;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<RegistrationResponseDTO>> registerUser(@Validated @RequestBody RegistrationRequestDTO registrationRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage, null), HttpStatus.BAD_REQUEST);
        }

        User registeredUser = userService.registerUser(registrationRequestDTO);
        RegistrationResponseDTO registrationResponse = new RegistrationResponseDTO(registeredUser);
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Successfully created user", registrationResponse), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponseDTO>> loginUser(@Validated @RequestBody LogInRequestDTO logInRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        String token = userService.logInUser(logInRequestDTO);
        return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "User login successful", new LoginResponseDTO(token)), HttpStatus.OK);
    }
}
