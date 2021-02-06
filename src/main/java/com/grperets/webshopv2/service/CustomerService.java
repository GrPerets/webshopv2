package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getById(Long id);
    void create(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> getAll();
}
