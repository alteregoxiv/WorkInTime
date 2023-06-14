package com.example.RegisterLogin.controller;

import com.example.RegisterLogin.dto.request.LogInRequestDTO;
import com.example.RegisterLogin.dto.request.TaskActionRequestDTO;
import com.example.RegisterLogin.dto.response.FilterResponseDTO;
import com.example.RegisterLogin.dto.response.TaskActionResponseDTO;
import com.example.RegisterLogin.service.impl.TaskServiceImpl;
import com.example.RegisterLogin.util.AuthorizationUtil;
import com.example.RegisterLogin.util.CommonResponse;
import com.example.RegisterLogin.util.RoleCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    AuthorizationUtil authorizationUtil;

    @Autowired
    RoleCheckUtil roleCheckUtil;

    @Autowired
    TaskServiceImpl taskService;

//    @PostMapping("/action")
//    public ResponseEntity<CommonResponse<TaskActionResponseDTO>> taskAction(@Validated @RequestBody TaskActionRequestDTO taskActionRequestDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
//            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            authorizationUtil.checkAuthorizationToken();
//            roleCheckUtil.checkUser();
//
//            List<Map<Long, String> activities = taskService.filterActivities(filterRequestDTO);
//            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Activities fetched", new TaskActionResponseDTO(activities)), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
}
