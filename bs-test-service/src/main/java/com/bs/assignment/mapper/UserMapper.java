package com.bs.assignment.mapper;

import com.bs.assignment.data.entity.User;
import com.bs.assignment.dto.CreateUserDto;
import com.bs.assignment.dto.UserInfoDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setUserName(createUserDto.getUserName());

        LocalDateTime now = LocalDateTime.now();
        user.setCreateDateTime(now);
        user.setUpdateDateTime(now);
        return user;
    }

    public UserInfoDto toUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setCreateDateTime(user.getCreateDateTime());
        userInfoDto.setUpdateDateTime(user.getUpdateDateTime());
        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setLastName(user.getLastName());
        userInfoDto.setUserName(user.getUserName());
        return userInfoDto;
    }
}
