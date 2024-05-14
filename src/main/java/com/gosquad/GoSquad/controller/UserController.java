package com.gosquad.GoSquad.controller;

import com.gosquad.GoSquad.entity.User;
import com.gosquad.GoSquad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
