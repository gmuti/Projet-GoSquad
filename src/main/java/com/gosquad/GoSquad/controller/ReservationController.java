package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Reservation;
import com.gosquad.GoSquad.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/reservations")
    public ResponseEntity list() {
        return new ResponseEntity(service.getReservations(), HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity(service.createReservation(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity updateReservation(@PathVariable("id") String id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return new ResponseEntity(service.updateReservation(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity deleteReservation(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteReservation(id), HttpStatus.OK);
    }
}
