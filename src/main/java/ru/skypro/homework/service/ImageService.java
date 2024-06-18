package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {

    Image uploadImage(MultipartFile image);

    Image getImage(int id);
}
