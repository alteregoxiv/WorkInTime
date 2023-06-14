package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Task;
import com.example.RegisterLogin.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskDAO extends JpaRepository<UserTask, Long> {

    @Query("SELECT ut.task FROM UserTask ut " +
            "WHERE ut.user.id = :userId " +
            "AND ut.task.productId IN :productIds " +
            "AND ut.task.activityId IN :activityIds " +
            "AND ut.task.startedAt IS NULL")
    List<Task> findTasksByUserAndByProductAndByActivityAndStartedAtIsNull(
            @Param("userId") Long userId,
            @Param("productIds") List<Long> productIds,
            @Param("activityIds") List<Long> activityIds
    ); // Assigned Tasks For Filter

    @Query("SELECT ut.task FROM UserTask ut " +
            "WHERE ut.user.id = :userId " +
            "AND ut.task.productId IN :productIds " +
            "AND ut.task.activityId IN :activityIds " +
            "AND ut.task.startedAt IS NOT NULL " +
            "AND ut.task.finishedAt IS NULL")
    List<Task> findTasksByUserAndByProductAndByActivityAndStartedAtIsNotNullAndFinishedAtIsNull(
            @Param("userId") Long userId,
            @Param("productIds") List<Long> productIds,
            @Param("activityIds") List<Long> activityIds
    ); // Pending Tasks For Filter

    @Query("SELECT ut.task FROM UserTask ut " +
            "WHERE ut.user.id = :userId " +
            "AND ut.task.productId IN :productIds " +
            "AND ut.task.activityId IN :activityIds " +
            "AND ut.task.startedAt IS NOT NULL " +
            "AND ut.task.finishedAt IS NOT NULL")
    List<Task> findTasksByUserAndByProductAndByActivityAndStartedAtIsNotNullAndFinishedAtIsNotNull(
            @Param("userId") Long userId,
            @Param("productIds") List<Long> productIds,
            @Param("activityIds") List<Long> activityIds
    ); // Finished Tasks For Filter

    @Query("SELECT ut.task FROM UserTask ut " +
            "WHERE ut.user.id = :userId " +
            "AND ut.task.startedAt IS NULL")
    List<Task> findTasksByUserAndStartedAtIsNull(
            @Param("userId") Long userId
    ); // Assigned Tasks By User

    @Query("SELECT ut.task FROM UserTask ut " +
            "WHERE ut.user.id = :userId " +
            "AND ut.task.startedAt IS NOT NULL " +
            "AND ut.task.finishedAt IS NULL")
    List<Task> findTasksByUserAndStartedAtIsNotNullAndFinishedAtIsNull(
            @Param("userId") Long userId
    ); // Pending Tasks By User
}
