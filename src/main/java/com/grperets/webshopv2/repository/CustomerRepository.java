package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
