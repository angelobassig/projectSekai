package com.company.discussion.services;

import org.springframework.http.ResponseEntity;

public interface FriendRequestService {

    // send friend request
    ResponseEntity sendFriendRequest(Long senderId, Long receiverId);

    // get friend requests
    ResponseEntity getFriendRequests(Long id);

    // delete friend request
    ResponseEntity cancelFriendRequest(Long senderId, Long receiverId);
}
