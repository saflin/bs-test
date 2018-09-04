package com.bs.assignment.controller;

import com.bs.assignment.dto.CreateUserDto;
import com.bs.assignment.dto.UpdateUserDto;
import com.bs.assignment.dto.UserInfoDto;
import com.bs.assignment.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create a user", nickname = "createUser")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto createUser(
            @ApiParam(value = "The details of the user to be created", required = true)
            @RequestBody @Valid CreateUserDto createUserDto) {
        //check user with user name already exists
        return userService.createUser(createUserDto);
    }

    @ApiOperation(value = "Update a user", nickname = "updateUser")
    @PutMapping(path = "/{id}")
    public UserInfoDto updateUser(
            @ApiParam(value = "The details of the user to be updated", required = true)
            @RequestBody @Valid UpdateUserDto updateUserDto,
            @PathVariable String id) {
        return userService.updateUser(updateUserDto, id);
    }

    @ApiOperation(value = "Get user by id", nickname = "getUserById")
    @GetMapping(path = "/{id}")
    public UserInfoDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Get all users", nickname = "getAllUsers")
    @GetMapping()
    public List<UserInfoDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
