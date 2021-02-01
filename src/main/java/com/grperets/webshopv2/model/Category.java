package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(name = "categoryname")
    public String categoryname;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    public List<Product> products;
}
