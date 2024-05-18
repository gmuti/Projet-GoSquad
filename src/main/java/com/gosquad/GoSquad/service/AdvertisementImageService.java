package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.AdvertisementImage;

import java.util.List;

public interface AdvertisementImageService {
    List<AdvertisementImage> getAdvertisementImages();

    Boolean createAdvertisementImage(AdvertisementImage advertisementImage);

    Boolean updateAdvertisementImage(AdvertisementImage advertisementImage);

    Boolean deleteAdvertisementImage(String id);
}
