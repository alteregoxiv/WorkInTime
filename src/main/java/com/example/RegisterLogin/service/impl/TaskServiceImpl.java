package com.example.RegisterLogin.service.impl;

import com.example.RegisterLogin.constants.Constants;
import com.example.RegisterLogin.dto.request.TaskCreateRequestDTO;
import com.example.RegisterLogin.exceptions.InvalidActionException;
import com.example.RegisterLogin.exceptions.InvalidStatusException;
import com.example.RegisterLogin.model.Task;
import com.example.RegisterLogin.model.Transaction;
import com.example.RegisterLogin.model.UserContext;
import com.example.RegisterLogin.repository.TaskDAO;
import com.example.RegisterLogin.repository.TransactionDAO;
import com.example.RegisterLogin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    UserContext userContext;

    public Task createTask(TaskCreateRequestDTO taskCreateRequestDTO) {
        Task newTask = new Task();
        Product product = productDAO.findBytId(taskCreateRequestDTO.getProductId());
        Activity activity = activityDAO.findById(taskCreateRequestDTO.getActivityId());

        if(product == null) {
            throw new NotFoundException("Product does not exist");
        }

        if(activity == null) {
            throw new NotFoundException("Activity does not exist");
        }

        newTask.setTaskName(taskCreateRequestDTO.getTaskName());
        newTask.setProductId(taskCreateRequestDTO.getProductId());
        newTask.setActivityId(taskCreateRequestDTO.getActivityId());
        newTask.setEstimatedTime(8);

        return taskDAO.save(newTask);
    }

    public Transaction initiateTaskAction(long taskId, Constants.TaskActions taskAction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setTask(taskDAO.findById(taskId));
        newTransaction.setUser(userContext.getUser());

        switch (taskAction) {
            case START -> {
                newTransaction.setActionName("start");
                return transactionDAO.save(newTransaction);
            }
            case PAUSE -> {
                newTransaction.setActionName("pause");
                return transactionDAO.save(newTransaction);
            }
            case END -> {
                newTransaction.setActionName("end");
                return transactionDAO.save(newTransaction);
            }
            default -> {
                throw new InvalidActionException("Status not valid");
            }
        }
    }
}
