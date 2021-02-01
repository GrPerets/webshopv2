package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.repository.CustomerRepository;
import com.grperets.webshopv2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);

    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
