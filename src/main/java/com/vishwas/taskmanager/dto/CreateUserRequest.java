package com.vishwas.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Enter valid email")
        String email,

        @Size(min = 8, message = "Password should be minimum of 8 characters")
        String password
) {
}
