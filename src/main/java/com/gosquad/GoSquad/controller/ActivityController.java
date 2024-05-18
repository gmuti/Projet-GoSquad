package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Activity;
import com.gosquad.GoSquad.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping("/activities")
    public ResponseEntity list() {
        return new ResponseEntity(service.getActivities(), HttpStatus.OK);
    }

    @PostMapping("/activity")
    public ResponseEntity createActivity(@RequestBody Activity activity) {
        return new ResponseEntity(service.createActivity(activity), HttpStatus.CREATED);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity updateActivity(@PathVariable("id") String id, @RequestBody Activity activity) {
        activity.setId(id);
        return new ResponseEntity(service.updateActivity(activity), HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity deleteActivity(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteActivity(id), HttpStatus.OK);
    }
}
