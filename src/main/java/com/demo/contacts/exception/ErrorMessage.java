package com.demo.contacts.exception;

public class ErrorMessage {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    String error;
    String description;
}
