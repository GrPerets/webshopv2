package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.Customer;
import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private List<RoleDTO> roles;

    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setUsername(this.username);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setPassword(this.password);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        //customer.setRoles(this.roles);
        return customer;
    }

    public static CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        List<RoleDTO> roleDTOS = customer.getRoles().stream().map(role ->RoleDTO.fromRole(role)).collect(Collectors.toList());
        customerDTO.setRoles(roleDTOS);

        return customerDTO;
    }

}
