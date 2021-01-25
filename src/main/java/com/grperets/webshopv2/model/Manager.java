package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "managers")
@Data
public class Manager extends BaseEntity{

    @Column(name = "managername")
    private String managername;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "managers_roles",
            joinColumns = {@JoinColumn(name = "manager_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;



}
