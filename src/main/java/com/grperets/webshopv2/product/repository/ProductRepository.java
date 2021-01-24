package com.grperets.webshopv2.product.repository;

import com.grperets.webshopv2.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
