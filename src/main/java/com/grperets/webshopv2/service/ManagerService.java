package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Manager;

import java.util.List;

public interface ManagerService {
    Manager getById(Long id);
    void save(Manager manager);
    void delete(Manager manager);
    List<Manager> getAll();
}