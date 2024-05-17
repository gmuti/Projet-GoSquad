package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.User;
import com.gosquad.GoSquad.service.UserService;
import com.gosquad.GoSquad.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity list()  {
        return new ResponseEntity(service.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user){
        return new ResponseEntity(service.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User user){
        user.setId(id);
        return new ResponseEntity(service.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id){
        return new ResponseEntity(service.deleteUser(id), HttpStatus.OK);
    }
}
