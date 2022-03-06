package com.company.discussion.controllers;

import com.company.discussion.models.Album;
import com.company.discussion.models.Photo;
import com.company.discussion.repositories.AlbumRepository;
import com.company.discussion.repositories.PhotoRepository;
import com.company.discussion.services.PhotoService;
import com.company.discussion.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // additional autowired interfaces
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // create photo
    @RequestMapping(value="/photos", method = RequestMethod.POST)
    public ResponseEntity<Object> createPhoto(@RequestBody Photo photo) {
        return photoService.createPhoto(photo);
    }

    // get photos
    @RequestMapping(value="/photos", method = RequestMethod.GET)
    public ResponseEntity<Object> getPhotos() {
        return photoService.getPhotos();
    }

    // add photo
    @RequestMapping(value="/photos/{albumId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addPhoto(@RequestBody Photo photo, @PathVariable Long albumId) {
        return photoService.addPhoto(photo, albumId);
    }

    // get photos from a particular album
    @RequestMapping(value="/photos/{albumId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPhotosFromAlbum(@PathVariable Long albumId) {
        return photoService.getPhotosFromAlbum(albumId);
    }

    // CODES FOR THYMELEAF TESTING
    public String addPhotoTL(Photo photo, Long albumId, MultipartFile multipartFile, Model model) throws IOException {
        // associate an album with the corresponding id: albumId
        Album album = albumRepository.findById(albumId).get();

        // create a new photo to be associated with the created album by using the setAlbum() method
        Photo newPhoto = new Photo();

        newPhoto.setTitle(photo.getTitle());
        newPhoto.setContent(photo.getContent());
        newPhoto.setComment(photo.getComment());
        newPhoto.setAlbum(album);

        // for cleaning the file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newPhoto.setPhotoFileName(fileName); //add photoFileName property in the Photo model

        photoRepository.save(newPhoto); // -> at this point, may record na lalabas sa database!

        // codes below are for uploading the files in the file system (which will be exposed later on)
        String uploadDir = "./user-photos/" + newPhoto.getAlbum().getUser().getId() + "/" + newPhoto.getAlbum().getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        // we're going to create a FileUploadUtil class

        return "displayImages";
    }

}
