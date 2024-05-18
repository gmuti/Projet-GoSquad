package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Group;
import com.gosquad.GoSquad.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("/groups")
    public ResponseEntity list() {
        return new ResponseEntity(service.getGroups(), HttpStatus.OK);

    }

    @PostMapping("/group")
    public ResponseEntity createGroup(@RequestBody Group group) {
        return new ResponseEntity(service.createGroup(group), HttpStatus.CREATED);
    }

    @PutMapping("/group/{id}")
    public ResponseEntity updateGroup(@PathVariable("id") String id, @RequestBody Group group) {
        group.setId(id);
        return new ResponseEntity(service.updateGroup(group), HttpStatus.OK);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity deleteGroup(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteGroup(id), HttpStatus.OK);
    }

    }
