package com.company.discussion.controllers;

import com.company.discussion.exceptions.UserException;
import com.company.discussion.models.User;
import com.company.discussion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @RequestMapping(value="/api/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // get users
    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return userService.getUsers();
    }

    // get user
    @RequestMapping(value="/api/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable Long userid) {
        return userService.getUser(userid);
    }

    // update user
    @RequestMapping(value="/api/users/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long userid) {
        return userService.updateUser(user, userid);
    }

    // search user
    @RequestMapping(value="/api/users/search", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(@RequestParam(value="name", defaultValue="") String searchTerm) {
        return userService.searchUser(searchTerm);
    }






    // User registration
    @RequestMapping(value="/users/register", method=RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Map<String, String> body) throws UserException {

        String email = body.get("email");
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String birthday = body.get("birthday");
        String gender = body.get("gender");
        String mobileNumber = body.get("mobileNumber");

        if(!userService.findByEmail(email).isEmpty()){
            throw new UserException("Email already exists.");
        } else {

            String password = body.get("password");
            String encodedPassword = new BCryptPasswordEncoder().encode(password);

//            User newUser = new User(email, encodedPassword);

            User newUser = new User(firstName, lastName, email, encodedPassword, birthday, gender, mobileNumber);

            userService.createUser(newUser);

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);

        }



    }
}
