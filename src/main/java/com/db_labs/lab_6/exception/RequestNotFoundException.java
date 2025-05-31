package com.db_labs.lab_6.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id) {
        super("Request with ID: " + id + " not found");
    }

    public RequestNotFoundException(String message) {
        super(message);
    }
}
