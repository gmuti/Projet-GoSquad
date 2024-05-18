package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Advertisement;
import com.gosquad.GoSquad.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvertisementController {

    @Autowired
    private AdvertisementService service;

    @GetMapping("/advertisements")
    public ResponseEntity<List<Advertisement>> list() {
        return new ResponseEntity<>(service.getAdvertisements(), HttpStatus.OK);
    }

    @PostMapping("/advertisement")
    public ResponseEntity createAdvertisement(@RequestBody Advertisement advertisement) {
        return new ResponseEntity(service.createAdvertisement(advertisement), HttpStatus.CREATED);
    }

    @PutMapping("/advertisement/{id}")
    public ResponseEntity updateAdvertisement(@PathVariable("id") String id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id);
        return new ResponseEntity(service.updateAdvertisement(advertisement), HttpStatus.OK);
    }

    @DeleteMapping("/advertisement/{id}")
    public ResponseEntity deleteAdvertisement(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteAdvertisement(id) ,HttpStatus.OK);
    }
}
