package com.company.discussion.services;

import com.company.discussion.models.Album;
import com.company.discussion.models.Photo;
import com.company.discussion.repositories.AlbumRepository;
import com.company.discussion.repositories.PhotoRepository;
import com.company.discussion.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // create photo
    public ResponseEntity createPhoto(Photo photo) {
        photoRepository.save(photo);
        return new ResponseEntity("Photo successfully created!", HttpStatus.CREATED);
    }

    // get photos
    public ResponseEntity getPhotos() {
        return new ResponseEntity(photoRepository.findAll(), HttpStatus.OK);
    }

    // add photo
    public ResponseEntity addPhoto(Photo photo, Long albumId) {
        // associate an album with the corresponding id: albumId
        Album album = albumRepository.findById(albumId).get();

        // create a new photo to be associated with the created album by using the setAlbum() method
        Photo newPhoto = new Photo();

        newPhoto.setTitle(photo.getTitle());
        newPhoto.setContent(photo.getContent());
        newPhoto.setComment(photo.getComment());
        newPhoto.setAlbum(album);
        photoRepository.save(newPhoto);

        return new ResponseEntity("Photo added successfully!", HttpStatus.CREATED);
    }

    // get photos from a particular album
    public ResponseEntity getPhotosFromAlbum(Long albumId) {
        // create a for-loop and a conditional statement that will be responsible for getting all of the photos given a particular albumId

        ArrayList<Photo> photoArr = new ArrayList<>();

        for (Photo photo : photoRepository.findAll()) {
            if (photo.getAlbum().getId() == albumId) {
                // get the records that satisfy the condition by storing them in an Array List
                photoArr.add(photo);
            }
        }
        return new ResponseEntity(photoArr, HttpStatus.OK);
    }

    // displaying photos from an album with id: albumId
    public ArrayList<String> displayGetPhotosFromAlbum(Long albumId) {
        // first, create an Array List
        ArrayList<String> stringUrlArr = new ArrayList<>();

        for (Photo photo : photoRepository.findAll()) {
            if (photo.getAlbum().getId() == albumId) {
                stringUrlArr.add("/user-photos/" + photo.getAlbum().getUser().getId() + "/" + photo.getAlbum().getId() + "/" + photo.getPhotoFileName());
            }
        }
        return stringUrlArr; // this contains all of the url for the images that satisfy the given albumId
    }

}
