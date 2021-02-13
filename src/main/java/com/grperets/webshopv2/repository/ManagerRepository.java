package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUsername(String username);
}
