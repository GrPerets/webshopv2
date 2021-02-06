package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagerDTO {
    private Long id;
    private String managername;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private List<RoleDTO> roles;

    public Manager toManager(){
        Manager manager = new Manager();
        manager.setId(this.id);
        manager.setManagername(this.managername);
        manager.setFirstName(this.firstName);
        manager.setLastName(this.lastName);
        manager.setPassword(this.password);
        manager.setEmail(this.email);
        manager.setPhone(this.phone);
        //manager.setRoles(this.roles);
        return manager;
    }

    public static ManagerDTO fromManager(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setId(manager.getId());
        managerDTO.setManagername(manager.getManagername());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setPassword(manager.getPassword());
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setPhone(manager.getPhone());
        List<RoleDTO> roleDTOS = manager.getRoles().stream().map(role -> RoleDTO.fromRole(role)).collect(Collectors.toList());
        managerDTO.setRoles(roleDTOS);

        return managerDTO;
    }



}
