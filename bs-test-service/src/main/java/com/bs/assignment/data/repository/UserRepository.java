package com.bs.assignment.data.repository;

import com.bs.assignment.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
