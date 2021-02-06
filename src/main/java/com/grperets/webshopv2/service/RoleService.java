package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface RoleService {

    Role getById(Long id);
    void create(Role role);
    void update(Role role);
    void delete(Role role);
    List<Role> getAll();
}
