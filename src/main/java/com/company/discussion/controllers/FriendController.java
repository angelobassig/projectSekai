package com.company.discussion.controllers;

import com.company.discussion.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendService friendService;

    // accepting friend
    @RequestMapping(value="/friends/{requesterId}/{recipientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> acceptFriend(@PathVariable Long requesterId, @PathVariable Long recipientId) {
        return friendService.acceptFriend(requesterId, recipientId);
    }

    // deleting friend
    @RequestMapping(value="/friends/{requesterId}/{recipientId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteFriend(@PathVariable Long requesterId, @PathVariable Long recipientId) {
        return friendService.deleteFriend(requesterId, recipientId);
    }

}
