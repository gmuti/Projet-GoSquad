package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Destination;
import com.gosquad.GoSquad.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationController {

    @Autowired
    private DestinationService service;

    @GetMapping("/destinations")
    public ResponseEntity list() {
        return new ResponseEntity(service.getDestinations(), HttpStatus.OK);
    }

    @PostMapping("/destination")
    public ResponseEntity createDestination(@RequestBody Destination destination) {
        return new ResponseEntity(service.createDestination(destination), HttpStatus.CREATED);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity updateDestination(@PathVariable("id") String id, @RequestBody Destination destination) {
        destination.setId(id);
        return new ResponseEntity(service.updateDestination(destination), HttpStatus.OK);
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity deleteDestination(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteDestination(id), HttpStatus.OK);
    }
}
