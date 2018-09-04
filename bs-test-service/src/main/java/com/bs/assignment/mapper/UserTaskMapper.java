package com.bs.assignment.mapper;

import com.bs.assignment.data.entity.TaskStatus;
import com.bs.assignment.data.entity.UserTask;
import com.bs.assignment.dto.CreateTaskDto;
import com.bs.assignment.dto.TaskInfoDto;
import org.springframework.stereotype.Component;

@Component
public class UserTaskMapper {

    public TaskInfoDto taskInfoDto(UserTask userTask){
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        taskInfoDto.setId(userTask.getId());
        taskInfoDto.setName(userTask.getName());
        taskInfoDto.setDescription(userTask.getDescription());
        taskInfoDto.setDateTime(userTask.getDateTime());
        taskInfoDto.setStatus(userTask.getStatus().name());
        return taskInfoDto;
    }

    public UserTask toEntity(CreateTaskDto createTaskDto){
        UserTask userTask = new UserTask();
        userTask.setName(createTaskDto.getName());
        userTask.setDescription(createTaskDto.getDescription());
        userTask.setDateTime(createTaskDto.getDateTime());
        userTask.setStatus(TaskStatus.PENDING);
        return userTask;
    }
}
