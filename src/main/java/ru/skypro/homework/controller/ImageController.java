package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.io.IOException;
@CrossOrigin(value = "http://localhost:8080")
@RestController
@RequestMapping("image")
public class ImageController{

    private ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile image) throws IOException {
        imageService.uploadImage(image);
        return ResponseEntity.ok().build();


    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
        headers.setContentLength(image.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(image.getData());
    }

}
