package com.company.discussion.repositories;

import com.company.discussion.models.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Object> {
}
