package com.bs.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateTaskDto extends BaseTaskDto {

    @Size(max = 255)
    @NotBlank
    @JsonProperty(value = "description")
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty(value = "date_time")
    private LocalDateTime dateTime;

    public CreateTaskDto() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
