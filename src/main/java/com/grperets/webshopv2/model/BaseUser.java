package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


@MappedSuperclass
@Data
public abstract class BaseUser extends BaseEntity{


    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "phone")
    @NotNull
    private String phone;

    private transient List<Role> roles;


}
