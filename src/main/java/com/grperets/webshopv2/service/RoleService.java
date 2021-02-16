package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface RoleService {
    Role findByRolename(String rolename);
    Role getRoleById(Long id);
    boolean createRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(Long id);
    List<Role> getAllRoles();
}
