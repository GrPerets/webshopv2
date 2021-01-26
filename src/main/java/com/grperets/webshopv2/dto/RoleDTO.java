package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Roles;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {

    private Roles rolename;

    public Role toRole(){
        Role role = new Role();
        role.setRolename(this.rolename);
        return role;
    }

    public static RoleDTO fromRole(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRolename(role.getRolename());
        return  roleDTO;
    }

}
