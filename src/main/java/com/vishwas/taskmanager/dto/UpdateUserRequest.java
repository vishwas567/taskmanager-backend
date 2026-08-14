package com.vishwas.taskmanager.dto;

import com.vishwas.taskmanager.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
            @NotBlank(message = "Name is required")
            String name,

            @Email(message = "Enter valid email")
            String email,

            @Size(min = 8, message = "Password should be minimum of 8 characters")
            String password,

            @NotNull(message = "Role is required")
            Role role

    ) {
    }

