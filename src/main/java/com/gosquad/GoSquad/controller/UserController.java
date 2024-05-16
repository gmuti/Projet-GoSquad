package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.User;
import com.gosquad.GoSquad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }
    @GetMapping("/user/{name}")
    public User getUser(@PathVariable String name) throws ExecutionException, InterruptedException {
        return userService.getUserDetails(name);
    }
}
