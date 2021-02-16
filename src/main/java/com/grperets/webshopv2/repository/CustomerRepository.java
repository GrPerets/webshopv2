package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
}
