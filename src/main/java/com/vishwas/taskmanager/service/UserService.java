package com.vishwas.taskmanager.service;


import com.vishwas.taskmanager.dto.CreateUserRequest;
import com.vishwas.taskmanager.dto.UserResponse;
import com.vishwas.taskmanager.entity.Role;
import com.vishwas.taskmanager.entity.User;
import com.vishwas.taskmanager.exception.ResourceNotFoundException;
import com.vishwas.taskmanager.mapper.UserMapper;
import com.vishwas.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = UserMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUserById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        return UserMapper.toResponse(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
