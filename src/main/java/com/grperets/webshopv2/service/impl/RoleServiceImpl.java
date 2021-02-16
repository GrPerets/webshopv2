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
    public Role findByRolename(String rolename) {
        return this.roleRepository.findByRolename(rolename);
    }

    @Override
    public Role getRoleById(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createRole(Role role) {
        if (this.roleRepository.save(role) != null){
            return true;
        }
        return false;

    }

    @Override
    public boolean updateRole(Role role){
        if (this.roleRepository.save(role) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRole(Long id) {
        Role role = getRoleById(id);
        if (role != null){
            this.roleRepository.delete(role);
            return true;
        }

        return false;

    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.findAll();
    }
}
