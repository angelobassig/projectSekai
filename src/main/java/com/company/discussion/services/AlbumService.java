package com.company.discussion.services;

import com.company.discussion.models.Album;
import org.springframework.http.ResponseEntity;

public interface AlbumService {

    // create album
    ResponseEntity createAlbum(Album album);

    // rename album
    ResponseEntity renameAlbum(Album album, Long id);

    // delete album
    ResponseEntity deleteAlbum(Long id);

    // add album
    ResponseEntity addAlbum(Album album, Long userId);

    // get photos from a particular album
    ResponseEntity getAlbumsFromUser(Long userId);

}
