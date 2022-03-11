package com.company.discussion.services;

import com.company.discussion.models.User;
import com.company.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Register the user credentials in the application

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check if username exists and get the user if it does
        User user = userRepository.findByEmail(email);
        // If no user found throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Store user credentials in User object for spring security
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

    }

}

