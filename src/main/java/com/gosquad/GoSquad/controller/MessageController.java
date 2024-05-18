package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.Message;
import com.gosquad.GoSquad.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping("/messages")
    public ResponseEntity list() {
        return new ResponseEntity(service.getMessages(), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity createMessage(@RequestBody Message message) {
        return new ResponseEntity(service.createMessage(message), HttpStatus.CREATED);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity updateMessage(@PathVariable("id") String id, @RequestBody Message message) {
        message.setId(id);
        return new ResponseEntity(service.updateMessage(message), HttpStatus.OK);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity deleteMessage(@PathVariable("id") String id) {
        return new ResponseEntity(service.deleteMessage(id), HttpStatus.OK);
    }
}
