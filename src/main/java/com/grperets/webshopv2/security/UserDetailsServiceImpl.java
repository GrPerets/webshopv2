package com.grperets.webshopv2.security;

import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.repository.CustomerRepository;
import com.grperets.webshopv2.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ManagerRepository managerRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserDetailsServiceImpl(ManagerRepository managerRepository, CustomerRepository customerRepository) {
        this.managerRepository = managerRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Manager manager = this.managerRepository.findByUsername(username);
        Customer customer = this.customerRepository.findByUsername(username);


        UserDetails userDetails = null;

        if (manager != null){
            userDetails = UserDetailsFactory.create(manager);
        } else

            if (customer != null){
                userDetails = UserDetailsFactory.create(customer);
            } else {
                log.info("IN loadUserByUsername user with username: {} not found!", username);
                throw new UsernameNotFoundException("User with username: " + username + " not found!");
            }


        log.info("IN loadUserByUsername " + username);
        return userDetails;

    }
}
