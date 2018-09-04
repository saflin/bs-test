package com.bs.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BaseUserDto {

    @Size(max = 50)
    @NotBlank
    @JsonProperty(value = "first_name")
    private String firstName;

    @Size(max = 50)
    @NotBlank
    @JsonProperty(value = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
