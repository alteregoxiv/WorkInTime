package com.example.RegisterLogin.controller;

import com.example.RegisterLogin.constants.Constants;
import com.example.RegisterLogin.dto.request.TaskCreateRequestDTO;
import com.example.RegisterLogin.dto.response.TaskActionResponseDTO;
import com.example.RegisterLogin.dto.response.TaskCreateResponseDTO;
import com.example.RegisterLogin.model.Task;
import com.example.RegisterLogin.model.Transaction;
import com.example.RegisterLogin.service.impl.TaskServiceImpl;
import com.example.RegisterLogin.util.AuthorizationUtil;
import com.example.RegisterLogin.util.CommonResponse;
import com.example.RegisterLogin.util.RoleCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<TaskCreateResponseDTO>> createTask(@Validated @RequestBody TaskCreateRequestDTO taskCreateRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        try {
            authorizationUtil.checkAuthorizationToken();
//            roleCheckUtil.checkManager();

            Task createdTask = taskService.createTask(taskCreateRequestDTO);
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(),
                    "Task Created",
                    new TaskCreateResponseDTO(
                    createdTask.getId(),
                    createdTask.getTaskName(),
                    createdTask.getCreatedAt(),
                    createdTask.getUpdatedAt()
                )
            ), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    e.getMessage()
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{taskId}/start")
    public ResponseEntity<CommonResponse<TaskActionResponseDTO>> startTask(BindingResult bindingResult, @PathVariable Long taskId) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        try {
            authorizationUtil.checkAuthorizationToken();
            roleCheckUtil.checkUser();

            Transaction taskAction = taskService.initiateTaskAction(taskId, Constants.TaskActions.START);
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(),
                    "Task Started",
                    new TaskActionResponseDTO(
                    taskAction.getTask().getId(),
                    taskAction.getTask().getTaskName(),
                    "start",
                    taskAction.getCreatedAt())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{taskId}/pause")
    public ResponseEntity<CommonResponse<TaskActionResponseDTO>> pauseTask(BindingResult bindingResult, @PathVariable Long taskId) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        try {
            authorizationUtil.checkAuthorizationToken();
            roleCheckUtil.checkUser();

            Transaction taskAction = taskService.initiateTaskAction(taskId, Constants.TaskActions.PAUSE);
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(),
                    "Task Paused",
                    new TaskActionResponseDTO(
                            taskAction.getTask().getId(),
                            taskAction.getTask().getTaskName(),
                            "pause",
                            taskAction.getCreatedAt())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{taskId}/end")
    public ResponseEntity<CommonResponse<TaskActionResponseDTO>> endTask(BindingResult bindingResult, @PathVariable Long taskId) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        try {
            authorizationUtil.checkAuthorizationToken();
            roleCheckUtil.checkUser();

            Transaction taskAction = taskService.initiateTaskAction(taskId, Constants.TaskActions.END);
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(),
                    "Task Completed",
                    new TaskActionResponseDTO(
                            taskAction.getTask().getId(),
                            taskAction.getTask().getTaskName(),
                            "end",
                            taskAction.getCreatedAt())), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
