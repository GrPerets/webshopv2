package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User getById(Long id);

    User create(User user);

    User update(User user);

    void delete(User user);

    List<User> getAll();
}
