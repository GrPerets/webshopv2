package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);
    List<Product> getAllProducts();
}
