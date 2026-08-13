package com.vishwas.taskmanager.dto;

import com.vishwas.taskmanager.entity.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}
