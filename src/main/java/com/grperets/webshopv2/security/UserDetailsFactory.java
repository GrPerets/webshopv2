package com.grperets.webshopv2.security;

import com.grperets.webshopv2.model.BaseUser;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailsFactory {

    public UserDetailsFactory() {
    }

    public static UserDetailsImpl create(BaseUser baseUser){
        return new UserDetailsImpl(
                baseUser.getId(),
                baseUser.getUsername(),
                baseUser.getFirstName(),
                baseUser.getLastName(),
                baseUser.getPassword(),
                baseUser.getEmail(),
                baseUser.getPhone(),
                baseUser.getStatus().equals(Status.ACTIVE),
                baseUser.getUpdated(),
                mapToGrantedAuthority(baseUser.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
    }
}
