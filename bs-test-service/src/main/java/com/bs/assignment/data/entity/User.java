package com.bs.assignment.data.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    public User() {
    }

    @Column(nullable = false, length = 255)
    private String userName;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private List<UserTask> tasks = new LinkedList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public List<UserTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<UserTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (!super.equals(o)) return false;
        if (!userName.equals(user.userName)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = result + super.hashCode();
        return result;
    }
}
