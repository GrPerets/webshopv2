package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.repository.RoleRepository;
import com.grperets.webshopv2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);

    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);

    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
