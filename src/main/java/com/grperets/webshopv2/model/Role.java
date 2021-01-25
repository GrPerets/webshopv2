package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{

    @Column(name = "rolename")
    private String rolename;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Manager> managers;
}
