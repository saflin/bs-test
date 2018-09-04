package com.bs.assignment.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getResponseStatus() {
        return NOT_FOUND;
    }
}
