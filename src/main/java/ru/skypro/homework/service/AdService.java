package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import java.io.IOException;

public interface AdService {
    AdsDTO getAllAds();

    AdDTO add(CreateOrUpdateAdDTO properties, MultipartFile image, String name) throws IOException;

    ExtendedAdDTO getAds(int id);

    void removeAd(int id);

    AdDTO updateAds(int id, CreateOrUpdateAdDTO properties);

    AdsDTO getAdsMe(String email);

    String[] updateImage(int id, MultipartFile image) throws IOException;
}

