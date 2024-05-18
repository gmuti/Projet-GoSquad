package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.AdvertisementImage;
import com.gosquad.GoSquad.service.AdvertisementImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvertisementImageController {

    @Autowired
    private AdvertisementImageService service;

    @GetMapping("/advertisementImages")
    public ResponseEntity list() {
        return new ResponseEntity(service.getAdvertisementImages(), HttpStatus.OK);
    }

    @PostMapping("/advertisementImage")
    public ResponseEntity createAdvertisementImage(@RequestBody AdvertisementImage advertisementImage) {
        return new ResponseEntity(service.createAdvertisementImage(advertisementImage), HttpStatus.CREATED);
    }

    @PutMapping("/advertisementImage/{id}")
    public ResponseEntity updateAdvertisementImage(@PathVariable("id") String id, @RequestBody AdvertisementImage advertisementImage) {
        advertisementImage.setId(id);
        return new ResponseEntity(service.updateAdvertisementImage(advertisementImage), HttpStatus.OK);
    }

    @DeleteMapping("/advertisementImage/{id}")
    public ResponseEntity<Void> deleteAdvertisementImage(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteAdvertisementImage(id),HttpStatus.OK);
    }
}
