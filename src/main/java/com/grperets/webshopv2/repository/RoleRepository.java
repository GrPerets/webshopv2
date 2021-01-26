package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolename(Roles rolename);
}
