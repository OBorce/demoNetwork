package com.example.demo.service;

import com.example.demo.entity.NetworkUser;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserDetailsRepository userRepository;
    private final NetworkUserService networkUserService;

    public UserDetailsService(UserDetailsRepository userRepository,
                              NetworkUserService networkUserService) {
        this.userRepository = userRepository;
        this.networkUserService = networkUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(userDetails -> {
                    var builder = User.withUsername(userDetails.getEmail());
                    builder.password(userDetails.getPassword());
                    builder.roles("USER");

                    return builder.build();
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public NetworkUser findNetworkUser(String username) {
        return userRepository.findByEmail(username)
                .map(userDetails -> networkUserService.findById(userDetails.getId()))
                .orElseThrow(UserNotFoundException::new);
    }
}
