package com.company.discussion.repositories;

import com.company.discussion.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Object> {
}
