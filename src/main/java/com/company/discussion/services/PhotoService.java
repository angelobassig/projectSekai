package com.company.discussion.services;

import com.company.discussion.models.Photo;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    // create photo
    ResponseEntity createPhoto(Photo photo);

    // get photos
    ResponseEntity getPhotos();

    // add photo
    ResponseEntity addPhoto(Photo photo, Long albumId);

    // get photos from a particular album
    ResponseEntity getPhotosFromAlbum(Long albumId);

}
