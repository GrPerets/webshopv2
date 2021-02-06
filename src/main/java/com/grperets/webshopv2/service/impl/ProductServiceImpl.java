package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Product;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.repository.ProductRepository;
import com.grperets.webshopv2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Product product) {
        product.setStatus(Status.NOT_AVAILABLE);
        product.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        this.productRepository.save(product);

    }

    @Override
    public void update(Product product){
        Product existingProduct = this.productRepository.findById(product.getId()).orElse(new Product());
        //existingProduct.setId(product.getId());
        existingProduct.setProductname(product.getProductname());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        this.productRepository.save(existingProduct);

    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);

    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }
}
