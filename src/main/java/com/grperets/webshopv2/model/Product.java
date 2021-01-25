package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Column(name = "productname")
    private String pruductname;


    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private BigDecimal price;



}