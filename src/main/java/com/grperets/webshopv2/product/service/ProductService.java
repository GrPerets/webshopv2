package com.grperets.webshopv2.product.service;

import com.grperets.webshopv2.product.model.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    void save(Product product);
    void delete(Long id);
    List<Product> getAll();
}
