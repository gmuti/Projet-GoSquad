package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.UserSettings;
import com.gosquad.GoSquad.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserSettingsController {

    @Autowired
    private UserSettingsService service;

    @GetMapping("/userSettings")
    public ResponseEntity list() {
        return new ResponseEntity<>(service.getUserSettings(), HttpStatus.OK);
    }

    @PostMapping("/userSettings")
    public ResponseEntity createUserSettings(@RequestBody UserSettings userSettings) {
        return new ResponseEntity(service.createUserSettings(userSettings), HttpStatus.CREATED);
    }

    @PutMapping("/userSettings/{id}")
    public ResponseEntity updateUserSettings(@PathVariable("id") String id, @RequestBody UserSettings userSettings) {
        userSettings.setId(id);
        return new ResponseEntity(service.updateUserSettings(userSettings), HttpStatus.OK);
    }

    @DeleteMapping("/userSettings/{id}")
    public ResponseEntity deleteUserSettings(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteUserSettings(id), HttpStatus.OK);
    }
}
