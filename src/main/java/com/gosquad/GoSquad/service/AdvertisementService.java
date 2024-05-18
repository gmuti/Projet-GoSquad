package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {
    Boolean createAdvertisement(Advertisement advertisement);

    Boolean updateAdvertisement(Advertisement advertisement);

    Boolean deleteAdvertisement(Long id);

    List<Advertisement> getAdvertisements();

    Boolean deleteAdvertisement(String id);
}
