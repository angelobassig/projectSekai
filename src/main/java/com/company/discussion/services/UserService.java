package com.company.discussion.services;

import com.company.discussion.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    // create user
    ResponseEntity createUser(User user);

    // get users
    ResponseEntity getUsers();

    // get user
    ResponseEntity getUser(Long id);

    // update user
    ResponseEntity updateUser(User user, Long id);

    // search user
    ResponseEntity searchUser(String searchTerm);

    // Optional - defined if the method may/may not return an object of the User class
    Optional<User> findByUsername(String username);
}
