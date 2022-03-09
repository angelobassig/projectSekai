package com.company.discussion.services;

import com.company.discussion.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //
    // creating a post
    ResponseEntity createPost(Post post, Long posterId, Long postedId);
}
