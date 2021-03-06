package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "managers")
@Data
public class Manager extends BaseUser {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "managers_roles",
            joinColumns = {@JoinColumn(name = "manager_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return getRoles().equals(manager.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoles());
    }
}
