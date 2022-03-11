package com.company.discussion.controllers;

import com.company.discussion.models.FriendRequest;
import com.company.discussion.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    // send friend request
    @RequestMapping(value="/api/friendRequests", method = RequestMethod.POST)
    public ResponseEntity<Object> sendFriendRequest(@RequestBody HashMap<String, Object> friendRequestMap) {
        return friendRequestService.sendFriendRequest(friendRequestMap);
    }

    // get friend requests
    @RequestMapping(value="/api/friendRequests/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendRequests(@PathVariable Long id) {
        return friendRequestService.getFriendRequests(id);
    }

//    // delete friend request
//    @RequestMapping(value="/api/friendRequests", method = RequestMethod.PUT)
//    public ResponseEntity<Object> cancelFriendRequest(@RequestBody HashMap<String, Object> friendRequestMap) {
//        return friendRequestService.cancelFriendRequest(friendRequestMap);
//    }

    // get one friend request
    @RequestMapping(value="/api/friendRequests/{senderId}/{receiverId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOneFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return friendRequestService.getOneFriendRequest(senderId, receiverId);
    }

    // cancel friend request
    @RequestMapping(value="/api/friendRequests/{senderId}/{receiverId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> cancelFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return friendRequestService.cancelFriendRequest(senderId, receiverId);
    }
}
