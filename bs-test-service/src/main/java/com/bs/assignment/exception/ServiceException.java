package com.bs.assignment.exception;

import org.springframework.http.HttpStatus;

public abstract class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public abstract HttpStatus getResponseStatus();

}
