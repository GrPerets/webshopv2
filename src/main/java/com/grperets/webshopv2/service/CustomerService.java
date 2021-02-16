package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.model.Role;

import java.util.List;

public interface CustomerService {
    Customer findByUsername(String username);
    Customer getCustomerById(Long id);
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(Long id);
    List<Customer> getAllCustomers();
}
