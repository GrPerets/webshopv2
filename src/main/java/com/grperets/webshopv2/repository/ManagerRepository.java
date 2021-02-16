package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUsername(String username);
    Manager findByEmail(String email);
    Manager findByPhone(String phone);

}
