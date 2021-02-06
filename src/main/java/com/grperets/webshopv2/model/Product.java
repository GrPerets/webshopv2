package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Column(name = "productname")
    private String productname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "price")
    private BigDecimal price;



}