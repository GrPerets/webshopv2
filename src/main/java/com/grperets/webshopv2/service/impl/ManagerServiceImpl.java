package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.repository.ManagerRepository;
import com.grperets.webshopv2.repository.RoleRepository;
import com.grperets.webshopv2.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public Manager getManagerById(Long id) {
        return this.managerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createManager(Manager manager) {
        if (this.managerRepository.findByUsername(manager.getUsername()) != null){
            //throw new ServiceException("Manager with username: " + manager.getUsername() + " is existing!");
            return false;
        }
        if (this.managerRepository.findByEmail(manager.getEmail()) != null){
            //throw new ServiceException("Manager with email: " + manager.getEmail() + " is existing!");
            return false;
        }
        if (this.managerRepository.findByPhone(manager.getPhone()) != null){
            //throw new ServiceException("Manager with phone: " + manager.getPhone() + " is existing!");
            return false;
        }

        Role role = this.roleRepository.findByRolename("ROLE_MANAGER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        manager.setRoles(roles);
        manager.setPassword(this.passwordEncoder.encode(manager.getPassword()));
        manager.setStatus(Status.ACTIVE);
        manager.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        if (this.managerRepository.save(manager) != null){
            log.info("IN createManager manager {} created", manager  );
            return true;
        }
        return false;

    }

    @Override
    public boolean updateManager(Manager manager){
        Manager existingManager = this.managerRepository.findById(manager.getId()).orElse(new Manager());
        //existingManager.setId(manager.getId());
        existingManager.setUsername(manager.getUsername());
        existingManager.setFirstName(manager.getFirstName());
        existingManager.setLastName(manager.getLastName());
        existingManager.setPassword(this.passwordEncoder.encode(manager.getPassword()));
        existingManager.setEmail(manager.getEmail());
        existingManager.setPhone(manager.getPhone());
        existingManager.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        if (this.managerRepository.save(existingManager) != null){
            log.info("IN updateManager manager {} updated", manager  );
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteManager(Long id) {
        Manager manager = getManagerById(id);
        if (manager != null){
            this.managerRepository.delete(manager);
            log.info("IN deleteManager manager {} deleted", manager  );
            return true;
        }
        return false;

    }

    @Override
    public List<Manager> getAllManagers() {
        return this.managerRepository.findAll();
    }
}
