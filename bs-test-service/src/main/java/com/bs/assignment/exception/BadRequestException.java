package com.bs.assignment.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ServiceException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getResponseStatus() {
        return BAD_REQUEST;
    }
}
