package com.company.discussion.repositories;

import com.company.discussion.models.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Object> {
}
