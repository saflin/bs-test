package com.bs.assignment.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK")
public class UserTask {

    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    @Column(nullable = false, updatable = true, length = 255)
    private String name;

    @Column(nullable = false, updatable = false, length = 255)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateTime;


    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    public UserTask() {
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserTask userTask = (UserTask) o;

        if (!id.equals(userTask.id)) return false;
        if (!name.equals(userTask.name)) return false;
        if (!description.equals(userTask.description)) return false;
        if (!dateTime.equals(userTask.dateTime)) return false;
        if (!status.equals(userTask.status)) return false;
        return user != null ? user.equals(userTask.user) : userTask.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
