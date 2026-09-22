package com.vishwas.taskmanager.exception;

public class WorkItemAccessDeniedException extends RuntimeException {
    public WorkItemAccessDeniedException(String message) {
        super(message);
    }
}
