package com.vishwas.taskmanager.exception;

public class WorkItemVersionConflictException extends RuntimeException {
    public WorkItemVersionConflictException(String message) {
        super(message);
    }
}
