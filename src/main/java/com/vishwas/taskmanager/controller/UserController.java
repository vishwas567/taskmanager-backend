package com.vishwas.taskmanager.controller;

import com.vishwas.taskmanager.dto.CreateUserRequest;
import com.vishwas.taskmanager.dto.UserResponse;
import com.vishwas.taskmanager.entity.User;
import com.vishwas.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){

        return userService.getUserById(id);

    }
}
