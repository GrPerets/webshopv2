package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "rolename")
    private Roles rolename;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Manager> managers;
}
