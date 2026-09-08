package com.vishwas.taskmanager.dto;

public record LoginRequest(
        String email,
        String password
) {
}
