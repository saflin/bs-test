package com.bs.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserDto extends BaseUserDto {

    @Size(max = 50)
    @NotBlank
    @JsonProperty(value = "username")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
