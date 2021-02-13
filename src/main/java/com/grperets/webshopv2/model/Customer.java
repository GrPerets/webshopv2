package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customers")
@Data
public class Customer extends BaseUser {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_roles",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
