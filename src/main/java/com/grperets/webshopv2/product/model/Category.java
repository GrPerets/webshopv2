package com.grperets.webshopv2.product.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity{

    @Column(name = "categoryname")
    public String categoryname;

    @OneToMany(mappedBy = "category")
    public List<Product> products;
}
