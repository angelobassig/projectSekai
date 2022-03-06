package com.company.discussion.controllers;

import com.company.discussion.models.User;
import com.company.discussion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @RequestMapping(value="/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // get users
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return userService.getUsers();
    }

    // get user
    @RequestMapping(value="/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable Long userid) {
        return userService.getUser(userid);
    }

    // update user
    @RequestMapping(value="/users/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long userid) {
        return userService.updateUser(user, userid);
    }
}
