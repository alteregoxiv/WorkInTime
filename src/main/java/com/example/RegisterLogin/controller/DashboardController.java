package com.example.RegisterLogin.controller;

import com.example.RegisterLogin.dto.response.AssignedTasksResponseDTO;
import com.example.RegisterLogin.dto.response.PendingTasksResponseDTO;
import com.example.RegisterLogin.model.UserContext;
import com.example.RegisterLogin.util.AuthorizationUtil;
import com.example.RegisterLogin.util.CommonResponse;
import com.example.RegisterLogin.dto.request.FilterRequestDTO;
import com.example.RegisterLogin.dto.response.AssignedProductsResponseDTO;
import com.example.RegisterLogin.dto.response.FilterResponseDTO;
import com.example.RegisterLogin.service.impl.DashboardServiceImpl;
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
@RequestMapping("/user")
public class DashboardController {

    @Autowired
    private DashboardServiceImpl dashboardService;

    @Autowired
    AuthorizationUtil authorizationUtil;

    @Autowired
    UserContext userContext;

    @Autowired
    RoleCheckUtil roleCheckUtil;

    @PostMapping("/filter")
    public ResponseEntity<CommonResponse<FilterResponseDTO>> filterActivities(@Validated @RequestBody FilterRequestDTO filterRequestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage), HttpStatus.BAD_REQUEST);
        }

        try {
            authorizationUtil.checkAuthenticationToken();
            roleCheckUtil.checkUser();

            List<Long> productIds = filterRequestDTO.getProduct();
            List<Long> activityIds = filterRequestDTO.getActivity();
            String status = filterRequestDTO.getStatus();

            if(activityIds.size() == 0) {
                activityIds = dashboardService.filteredActivityIds(productIds);
            }
            List<Map<Long, String>> activities = dashboardService.filterActivities(productIds);
            List<Map<Long, String>> tasks = dashboardService.filterTasks(productIds, activityIds, status);
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Activities and Tasks fetched", new FilterResponseDTO(activities, tasks)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<CommonResponse<AssignedProductsResponseDTO>> assignedProducts() {

        try {
            authorizationUtil.checkAuthenticationToken();
            roleCheckUtil.checkUser();

            List<Map<Long, String>> products = dashboardService.assignedProducts(userContext.getUser());
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Products fetched", new AssignedProductsResponseDTO(products)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/tasks/pending")
    public ResponseEntity<CommonResponse<PendingTasksResponseDTO>> pendingTasks() {

        try {
            authorizationUtil.checkAuthenticationToken();
            roleCheckUtil.checkUser();

            List<Map<Long, String>> tasks = dashboardService.pendingTasks(userContext.getUser());
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Pending Tasks fetched", new PendingTasksResponseDTO(tasks)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/tasks/assigned")
    public ResponseEntity<CommonResponse<AssignedTasksResponseDTO>> assignedTasks() {

        try {
            authorizationUtil.checkAuthenticationToken();
            roleCheckUtil.checkUser();

            List<Map<Long, String>> tasks = dashboardService.assignedTasks(userContext.getUser());
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.OK.value(), "Assigned Tasks fetched", new AssignedTasksResponseDTO(tasks)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CommonResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
