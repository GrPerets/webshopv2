package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private List<RoleDTO> roles;

    public User toUser(){
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        //manager.setRoles(this.roles);
        return user;
    }

    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        List<RoleDTO> roleDTOS = user.getRoles().stream().map(role -> RoleDTO.fromRole(role)).collect(Collectors.toList());
        userDTO.setRoles(roleDTOS);

        return userDTO;
    }
}
