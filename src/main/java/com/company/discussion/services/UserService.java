package com.company.discussion.services;

import com.company.discussion.models.User;
import org.springframework.http.ResponseEntity;

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
}
