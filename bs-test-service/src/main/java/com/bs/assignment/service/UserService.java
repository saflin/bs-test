package com.bs.assignment.service;

import com.bs.assignment.data.entity.User;
import com.bs.assignment.data.repository.UserRepository;
import com.bs.assignment.dto.CreateUserDto;
import com.bs.assignment.dto.UpdateUserDto;
import com.bs.assignment.dto.UserInfoDto;
import com.bs.assignment.exception.BadRequestException;
import com.bs.assignment.exception.ResourceNotFoundException;
import com.bs.assignment.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserInfoDto createUser(CreateUserDto createUserDto) {
        User newUser = userMapper.toEntity(createUserDto);
        newUser = userRepository.save(newUser);
        return userMapper.toUserInfoDto(newUser);
    }

    public UserInfoDto updateUser(UpdateUserDto updateUserDto, String id) {
        User user = getUser(id);
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateDateTime(now);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user = userRepository.save(user);
        return userMapper.toUserInfoDto(user);
    }

    public UserInfoDto getUserById(String id) {
        User user = getUser(id);
        return userMapper.toUserInfoDto(user);
    }

    public List<UserInfoDto> getAllUsers() {
        //this wont scale for large data set.
        //should use pagination.
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toUserInfoDto(user))
                .collect(Collectors.toList());
    }

    public User getUser(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Invalid user identifier");
        }
        Optional<User> optional = userRepository.findById(id);
        User user = optional.orElseThrow(() -> new ResourceNotFoundException("Unable to find user with id:" + id));
        return user;
    }
}
