package com.vishwas.taskmanager.security;

import com.vishwas.taskmanager.repository.UserRepository;
import com.vishwas.taskmanager.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustonUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(
                                "User not found with email: "+ email
                        ));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
