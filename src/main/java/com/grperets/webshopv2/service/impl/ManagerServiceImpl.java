package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.repository.ManagerRepository;
import com.grperets.webshopv2.repository.RoleRepository;
import com.grperets.webshopv2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Manager findByUsername(String username){
        return this.managerRepository.findByUsername(username);
    }

    @Override
    public Manager getById(Long id) {
        return this.managerRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Manager manager) {
        Role role = this.roleRepository.findByRolename("ROLE_MANAGER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        manager.setRoles(roles);
        manager.setPassword(this.passwordEncoder.encode(manager.getPassword()));
        manager.setStatus(Status.ACTIVE);
        manager.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        this.managerRepository.save(manager);

    }

    @Override
    public void update(Manager manager){
        Manager existingManager = this.managerRepository.findById(manager.getId()).orElse(new Manager());
        //existingManager.setId(manager.getId());
        existingManager.setUsername(manager.getUsername());
        existingManager.setFirstName(manager.getFirstName());
        existingManager.setLastName(manager.getLastName());
        existingManager.setPassword(this.passwordEncoder.encode(manager.getPassword()));
        existingManager.setEmail(manager.getEmail());
        existingManager.setPhone(manager.getPhone());
        existingManager.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        this.managerRepository.save(existingManager);
    }

    @Override
    public void delete(Manager manager) {
        this.managerRepository.delete(manager);

    }

    @Override
    public List<Manager> getAll() {
        return this.managerRepository.findAll();
    }
}
