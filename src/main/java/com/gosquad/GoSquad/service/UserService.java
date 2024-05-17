package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    Boolean createUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(String id);
}
