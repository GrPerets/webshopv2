package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    void create(Product product);
    void update(Product product);
    void delete(Product product);
    List<Product> getAll();
}
