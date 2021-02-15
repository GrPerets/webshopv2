package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "managers")
@Data
public class Manager extends BaseUser {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "managers_roles",
            joinColumns = {@JoinColumn(name = "manager_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
