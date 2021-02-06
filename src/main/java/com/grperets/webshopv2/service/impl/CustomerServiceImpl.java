package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.repository.CustomerRepository;
import com.grperets.webshopv2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Customer getById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Customer customer) {
        customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
        customer.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        this.customerRepository.save(customer);

    }

    @Override
    public void update(Customer customer){
        Customer existingCustomer = this.customerRepository.findById(customer.getId()).orElse(new Customer());
        //existingCustomer.setId(customer.getId());
        existingCustomer.setCustomername(customer.getCustomername());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        this.customerRepository.save(existingCustomer);
    }


    @Override
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);

    }

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }
}
