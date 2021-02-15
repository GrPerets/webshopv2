package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface ManagerService {
    Manager findByUsername(String username);
    Manager getById(Long id);
    void create(Manager manager);
    void update(Manager manager);
    void delete(Manager manager);
    List<Manager> getAll();
}
