package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface CustomerService {
    Customer findByUsername(String username);
    Customer getById(Long id);
    void create(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> getAll();
}
