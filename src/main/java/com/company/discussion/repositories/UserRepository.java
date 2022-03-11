package com.company.discussion.repositories;

import com.company.discussion.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Object> {
    // Custom method to find a user using a username
    User findByEmail(String email);
}
