package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface ManagerService {
    Manager findByUsername(String username);

    Manager getManagerById(Long id);
    boolean createManager(Manager manager);
    boolean updateManager(Manager manager);
    boolean deleteManager(Long id);
    List<Manager> getAllManagers();
}
