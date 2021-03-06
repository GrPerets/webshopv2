package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "customers")
@Data
public class Customer extends BaseUser {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_roles",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return getRoles().equals(customer.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoles());
    }
}
