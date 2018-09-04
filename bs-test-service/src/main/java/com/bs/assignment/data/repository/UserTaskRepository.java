package com.bs.assignment.data.repository;

import com.bs.assignment.data.entity.TaskStatus;
import com.bs.assignment.data.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, String> {

    @Query("SELECT t FROM UserTask t WHERE t.status = :taskStatus and t.dateTime < CURRENT_TIMESTAMP")
    List<UserTask> findAllPendingTask(@Param("taskStatus") TaskStatus taskStatus);

}
