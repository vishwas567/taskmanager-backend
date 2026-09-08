package com.vishwas.taskmanager.mapper;

import com.vishwas.taskmanager.dto.CreateUserRequest;
import com.vishwas.taskmanager.dto.UpdateUserRequest;
import com.vishwas.taskmanager.dto.UserResponse;
import com.vishwas.taskmanager.entity.Role;
import com.vishwas.taskmanager.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserResponse toResponse(User user){
        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return response;
    }

    public static User toEntity(CreateUserRequest request){
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
//        user.setPassword(request.password());

        user.setRole(Role.USER);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;

    }

    public static void updateEntity(User user, UpdateUserRequest request){
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(request.role());
        user.setUpdatedAt(LocalDateTime.now());

    }
}
