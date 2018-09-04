package com.bs.assignment.dto;

public class TaskInfoDto extends CreateTaskDto {

    private String id;

    private String status;

    public TaskInfoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
