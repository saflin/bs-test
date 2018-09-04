package com.bs.assignment.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BaseTaskDto {

    @Size(max = 255)
    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    public BaseTaskDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
