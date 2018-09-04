package com.bs.assignment.controller;

import com.bs.assignment.dto.CreateTaskDto;
import com.bs.assignment.dto.TaskInfoDto;
import com.bs.assignment.dto.UpdateTaskDto;
import com.bs.assignment.service.UserTaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/{userId}/task")
public class UserTaskController {

    private UserTaskService userTaskService;

    @Autowired
    public UserTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @ApiOperation(value = "Create task for a user", nickname = "createTask")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskInfoDto createTask(
            @ApiParam(value = "The details of the task about to be created", required = true)
            @RequestBody @Valid CreateTaskDto createTaskDto,
            @PathVariable String userId) {
        return userTaskService.createTask(createTaskDto, userId);
    }

    @ApiOperation(value = "Get task by id", nickname = "getTaskById")
    @GetMapping(path = "/{taskId}")
    public TaskInfoDto getTaskById(@PathVariable String taskId) {
        return userTaskService.getTaskById(taskId);
    }

    @ApiOperation(value = "Get  all tasks for a user", nickname = "getAllTasksForUser")
    @GetMapping()
    public List<TaskInfoDto> getAllTasksForUser(@PathVariable String userId) {
        return userTaskService.getAllTasksForUser(userId);
    }

    @ApiOperation(value = "Update task", nickname = "updateTask")
    @PutMapping(path = "/{taskId}")
    public TaskInfoDto updateTask(
            @ApiParam(value = "The details of the task about to be updated", required = true)
            @RequestBody @Valid UpdateTaskDto updateTaskDto,
            @PathVariable String taskId, @PathVariable String userId) {
        return userTaskService.updateTask(userId, taskId, updateTaskDto);

    }

    @ApiOperation(value = "Delete a task", nickname = "deleteTask")
    @DeleteMapping(path = "/{taskId}")
    public void deleteTask(
            @PathVariable String taskId, @PathVariable String userId) {
        userTaskService.deleteTask(userId, taskId);
    }
}
