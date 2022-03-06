package com.company.discussion.repositories;

import com.company.discussion.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Object> {
}
