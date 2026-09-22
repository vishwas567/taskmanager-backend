package com.vishwas.taskmanager.exception;

public class InvalidWorkItemTransitionException extends RuntimeException{

    public InvalidWorkItemTransitionException(String message){
        super(message);
    }
}
