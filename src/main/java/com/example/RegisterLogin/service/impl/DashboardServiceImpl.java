package com.example.RegisterLogin.service.impl;

import com.example.RegisterLogin.exceptions.InvalidStatusException;
import com.example.RegisterLogin.model.*;
import com.example.RegisterLogin.repository.*;
import com.example.RegisterLogin.service.DashboardService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    ActivityDAO activityDAO;

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserTaskDAO userTaskDAO;

    @Autowired
    UserContext userContext;

    @Autowired
    UserProductDAO userProductDAO;

    private List<Map<Long, String>> changeActivityFormat(List<Activity> activities) {
        List<Map<Long, String>> result = new ArrayList<>();

        for (Activity activity : activities) {
            Map<Long, String> map = new HashMap<>();
            map.put(activity.getId(), activity.getActivityName());
            result.add(map);
        }

        return result;
    }

    private List<Long> getFilteredActivityIds(List<Activity> activities) {
        List<Long> result = new ArrayList<>();

        for (Activity activity : activities) {
            result.add(activity.getId());
        }

        return result;
    }

    private List<Map<Long, String>> changeTaskFormat(List<Task> tasks) {
        List<Map<Long, String>> result = new ArrayList<>();

        for (Task task : tasks) {
            Map<Long, String> map = new HashMap<>();
            map.put(task.getId(), task.getTaskName());
            result.add(map);
        }

        return result;
    }

    private List<Map<Long, String>> changeProductFormat(List<Product> products) {
        List<Map<Long, String>> result = new ArrayList<>();

        for (Product product : products) {
            Map<Long, String> map = new HashMap<>();
            map.put(product.getId(), product.getProductName());
            result.add(map);
        }

        return result;
    }

    public List<Map<Long, String>> filterActivities(List<Long> productIds) {
        List<Activity> activities = activityDAO.findActivitiesByProductIds(productIds);
        return changeActivityFormat(activities);
    }

    public List<Long> filteredActivityIds(List<Long> productIds) {
        List<Activity> activities = activityDAO.findActivitiesByProductIds(productIds);
        return getFilteredActivityIds(activities);
    }

    public List<Map<Long, String>> filterTasks(List<Long> productIds, List<Long> activityIds, String status) {
        List<Task> tasks;

        switch (status) {
            case "completed" ->
                    tasks = userTaskDAO.findTasksByUserAndByProductAndByActivityAndStartedAtIsNotNullAndFinishedAtIsNotNull(
                            (long) userContext.getUser().getId(),
                            productIds,
                            activityIds
                    );
            case "pending" ->
                    tasks = userTaskDAO.findTasksByUserAndByProductAndByActivityAndStartedAtIsNotNullAndFinishedAtIsNull(
                            (long) userContext.getUser().getId(),
                            productIds,
                            activityIds
                    );
            case "assigned" -> tasks = userTaskDAO.findTasksByUserAndByProductAndByActivityAndStartedAtIsNull(
                    (long) userContext.getUser().getId(),
                    productIds,
                    activityIds
            );
            default -> {
                throw new InvalidStatusException("Status not valid");
            }
        }
        return changeTaskFormat(tasks);
    }

    public List<Map<Long, String>> assignedProducts(User user) {
        try {
            List<Product> products = userProductDAO.findProductsByUserId(user);
            return changeProductFormat(products);
        } catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    public List<Map<Long, String>> pendingTasks(User user) {
        try {
            List<Task> pendingTasks = userTaskDAO.findTasksByUserAndStartedAtIsNotNullAndFinishedAtIsNull((long) user.getId());
            return changeTaskFormat(pendingTasks);
        } catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    public List<Map<Long, String>> assignedTasks(User user) {
        try {
            List<Task> assignedTasks = userTaskDAO.findTasksByUserAndStartedAtIsNull((long) user.getId());
            return changeTaskFormat(assignedTasks);
        } catch(NoResultException e) {
            return new ArrayList<>();
        }
    }
}
