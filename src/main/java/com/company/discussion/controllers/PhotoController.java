package com.company.discussion.controllers;

import com.company.discussion.models.Album;
import com.company.discussion.models.Photo;
import com.company.discussion.repositories.AlbumRepository;
import com.company.discussion.repositories.PhotoRepository;
import com.company.discussion.services.PhotoService;
import com.company.discussion.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
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

    @GetMapping("/")
    public String UploadPage(Model model) {
        Photo photo = new Photo();
        Album album = new Album();
        model.addAttribute("photo", photo);
        model.addAttribute("album", album);
        return "home";
    }

//    @PostMapping("/photos/save/{albumId}")
//    public String addPhotoTL(Photo photo, @PathVariable Long albumId, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
//
//        // associate an album with the corresponding id: albumId
//        Album album = albumRepository.findById(albumId).get();
//
//        // create a new photo to be associated with the created album by using the setAlbum() method
//        Photo newPhoto = new Photo();
//
//        newPhoto.setTitle(photo.getTitle());
//        newPhoto.setContent(photo.getContent());
//        newPhoto.setComment(photo.getComment());
//        newPhoto.setAlbum(album);
//
//        // for cleaning the file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        newPhoto.setPhotoFileName(fileName); //add photoFileName property in the Photo model
//
//        photoRepository.save(newPhoto); // -> at this point, may record na lalabas sa database!
//
//        // codes below are for uploading the files in the file system (which will be exposed later on)
//        String uploadDir = "./user-photos/" + newPhoto.getAlbum().getUser().getId() + "/" + newPhoto.getAlbum().getId();
//
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        // we're going to create a FileUploadUtil class
//
//        return "home";
//    }

    // page for displaying all of the photos given a particular album id

    // page for displaying all of the albums (with photos) given a particular user id

    @PostMapping("/tl/photos")
    public String createPhotoTl(Photo photo, @RequestParam("albumId") Long albumId, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        // associate an album with the corresponding id: albumId
        Album album = albumRepository.findById(albumId).get();

        // create a new photo to be associated with the created album by using the setAlbum() method
        Photo newPhoto = new Photo();

        newPhoto.setTitle(photo.getTitle());
        newPhoto.setContent(photo.getContent());
        newPhoto.setComment(photo.getComment());
        newPhoto.setAlbum(album);

        // first save is here, just to get the photo Id for the codes below
        photoRepository.save(newPhoto);

        // for cleaning the file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newPhoto.setPhotoFileName(newPhoto.getId() + fileName); //add photoFileName property in the Photo model

        photoRepository.save(newPhoto); // -> at this point, may record na lalabas sa database!

        // codes below are for uploading the files in the file system (which will be exposed later on)
        String uploadDir = "./user-photos/" + newPhoto.getAlbum().getUser().getId() + "/" + newPhoto.getAlbum().getId();

        // IMPORTANT NOTE: added the ' + newPhoto.getId() ' to somehow create a unique image after every upload
        FileUploadUtil.saveFile(uploadDir, newPhoto.getId() + fileName, multipartFile);
        // we're going to create a FileUploadUtil class

        return "result";
    }

    // form/page for asking the user for an albumId input, where upon clicking the submit button, the user is directed to the list of images pertaining to the albumId inputted
    @GetMapping("/tl/form/getAlbumId")
    public String formGetAlbumId(Model model) {
        Photo photo = new Photo();
        Album album = new Album();
        model.addAttribute("photo", photo);
        model.addAttribute("album", album);
        return "formGetAlbumId";
    }

    // displaying photos from an album with id: albumId
    @GetMapping("/tl/display/albums")
    public String displayGetPhotosFromAlbum(@RequestParam("albumId") Long albumId, Model model) {
        // first, create an Array List
        ArrayList<String> stringUrlArr = new ArrayList<>();

        for (Photo photo : photoRepository.findAll()) {
            if (photo.getAlbum().getId() == albumId) {
                stringUrlArr.add("/user-photos/" + photo.getAlbum().getUser().getId() + "/" + photo.getAlbum().getId() + "/" + photo.getPhotoFileName());
            }
        }
        // adding stringUrlArr to the model to be able to use it in ThymeLeaf
        model.addAttribute("stringUrlArr", stringUrlArr);

        return "displayPhotos"; // this contains all of the url for the images that satisfy the given albumId
    }

}
