package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
