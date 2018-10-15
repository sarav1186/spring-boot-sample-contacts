package com.demo.contacts.exception;

public class ConflictException extends RuntimeException {

    private String message;
    private Throwable cause;

    public ConflictException(String message) {
        this.message = message;
    }

    public ConflictException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
