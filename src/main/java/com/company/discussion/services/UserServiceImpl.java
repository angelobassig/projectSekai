package com.company.discussion.services;

import com.company.discussion.models.User;
import com.company.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // create user
    public ResponseEntity createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity("User created successfully!", HttpStatus.CREATED);
    }

    // get users
    public ResponseEntity getUsers() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    // get user
    public ResponseEntity getUser(Long id) {
        User user = userRepository.findById(id).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // update user
    public ResponseEntity updateUser(User user, Long id) {
        User userToUpdate = userRepository.findById(id).get();

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthday(user.getBirthday());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setMobileNumber(user.getMobileNumber());
        userToUpdate.setOnline(user.isOnline());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setLastName(user.getLastName());

        userRepository.save(userToUpdate);

        return new ResponseEntity("User updated successfully!", HttpStatus.OK);
    }
}
