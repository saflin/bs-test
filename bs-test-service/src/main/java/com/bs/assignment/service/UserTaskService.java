package com.bs.assignment.service;

import com.bs.assignment.data.entity.TaskStatus;
import com.bs.assignment.data.entity.User;
import com.bs.assignment.data.entity.UserTask;
import com.bs.assignment.data.repository.UserTaskRepository;
import com.bs.assignment.dto.CreateTaskDto;
import com.bs.assignment.dto.TaskInfoDto;
import com.bs.assignment.dto.UpdateTaskDto;
import com.bs.assignment.exception.BadRequestException;
import com.bs.assignment.exception.ResourceNotFoundException;
import com.bs.assignment.mapper.UserTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTaskService.class);

    private UserService userService;

    private UserTaskMapper userTaskMapper;

    private UserTaskRepository userTaskRepository;

    @Autowired
    public UserTaskService(UserService userService, UserTaskMapper userTaskMapper,
                           UserTaskRepository userTaskRepository) {
        this.userService = userService;
        this.userTaskMapper = userTaskMapper;
        this.userTaskRepository = userTaskRepository;

    }

    @Transactional
    public TaskInfoDto createTask(CreateTaskDto createTaskDto, String userId) {
        //avoid adding duplicate task.
        User user = userService.getUser(userId);
        UserTask userTask = userTaskMapper.toEntity(createTaskDto);
        userTask.setUser(user);
        user.getTasks().add(userTask);
        userTask = userTaskRepository.save(userTask);
        return userTaskMapper.taskInfoDto(userTask);
    }


    public TaskInfoDto getTaskById(String taskId){
        if (taskId == null || taskId.isEmpty()) {
            throw new BadRequestException("Invalid task identifier");
        }
        Optional<UserTask> optional = userTaskRepository.findById(taskId);
        UserTask userTask = optional.orElseThrow(() ->
                new ResourceNotFoundException("Unable to find task with id:" + taskId));
        return userTaskMapper.taskInfoDto(userTask);
    }

    @Transactional
    public List<TaskInfoDto> getAllTasksForUser(String userId) {
        User user = userService.getUser(userId);
        return user.getTasks()
                .stream()
                .map(userTask -> userTaskMapper.taskInfoDto(userTask))
                .collect(Collectors.toList());

    }

    @Transactional
    public TaskInfoDto updateTask(String userId, String taskId, UpdateTaskDto updateTaskDto) {
        UserTask userTask = getTaskForUser(userId, taskId);
        userTask.setName(updateTaskDto.getName());
        userTask = userTaskRepository.save(userTask);
        return userTaskMapper.taskInfoDto(userTask);
    }

    private UserTask getTaskForUser(String userId, String taskId){
        if (taskId == null || taskId.isEmpty()) {
            throw new BadRequestException("Invalid task identifier");
        }
        User user = userService.getUser(userId);
        UserTask userTask = user.getTasks()
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Task doesnt exist for user"));
        return userTask;
    }

    @Transactional
    public void deleteTask(String userId, String taskId) {
        UserTask userTask = getTaskForUser(userId, taskId);
        User user = userTask.getUser();
        userTaskRepository.delete(userTask);
    }

    @Scheduled(cron = "${cron.expression}")
    @Transactional
    public void updatePendingTasks(){
        LOGGER.info("Running task clean up job at "+LocalDateTime.now());
        List<UserTask> list = userTaskRepository.findAllPendingTask(TaskStatus.PENDING);
        LOGGER.info("Found "+list.size()+" old pending tasks.");
        list.stream().forEach(userTask -> {
            try {
                LOGGER.info("Updating old pending task: "+userTask.getId()+ " to done.");
                userTask.setStatus(TaskStatus.DONE);
                userTaskRepository.save(userTask);
                LOGGER.info("task updated successfully.");

            }catch (Exception e){
                LOGGER.error("exception while updating task :"+userTask.getId(), e);
            }
        });
    }
}
