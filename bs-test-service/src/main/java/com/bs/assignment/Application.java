package com.bs.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class for the application
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.bs.assignment.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
