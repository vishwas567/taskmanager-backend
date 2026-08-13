package com.vishwas.taskmanager.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messaage){
        super(messaage);
    }

}
