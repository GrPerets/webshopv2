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
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createProduct(Product product) {
        if (this.productRepository.findByProductname(product.getProductname()) != null){
            return false;
        }
        product.setStatus(Status.NOT_AVAILABLE);
        product.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        if (this.productRepository.save(product) != null){
            return true;
        }
        return false;

    }

    @Override
    public boolean updateProduct(Product product){
        Product existingProduct = this.productRepository.findById(product.getId()).orElse(new Product());
        //existingProduct.setId(product.getId());
        existingProduct.setProductname(product.getProductname());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUpdated(Timestamp.valueOf(LocalDateTime.now()));

        if (this.productRepository.save(existingProduct) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = getProductById(id);
        if (product != null){
            this.productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
