package com.company.discussion.services;

import com.company.discussion.models.FriendRequest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface FriendRequestService {

    // send friend request
    ResponseEntity sendFriendRequest(HashMap<String, Object> friendRequestMap);

    // get friend requests
    ResponseEntity getFriendRequests(Long id);

//    // delete friend request
//    ResponseEntity cancelFriendRequest(HashMap<String, Object> friendRequestMap);

    // get one friend request
    ResponseEntity getOneFriendRequest(Long senderId, Long receiverId);

    // delete one friend request
    ResponseEntity cancelFriendRequest(Long senderId, Long receiverId);
}
