package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserService userService;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new AdsDTO(ads.size(), ads);
    }

    @Override
    public AdDTO add(CreateOrUpdateAdDTO properties, MultipartFile image, String email) {
        Ad ad = adMapper.createOrUpdateAdToAd(properties, userService.getUser(email));
        ad.setImage(imageService.saveImage(image));
        return adMapper.modelToAdDTO(adRepository.save(ad));
    }
}
