package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Roles;
import com.grperets.webshopv2.repository.ManagerRepository;
import com.grperets.webshopv2.repository.RoleRepository;
import com.grperets.webshopv2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository, RoleRepository roleRepository) {
        this.managerRepository = managerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Manager getById(Long id) {
        return managerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Manager manager) {
        Role role = roleRepository.findByRolename(Roles.ROLE_MANAGER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        manager.setRoles(roles);
        managerRepository.save(manager);

    }

    @Override
    public void delete(Manager manager) {
        managerRepository.delete(manager);

    }

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }
}
