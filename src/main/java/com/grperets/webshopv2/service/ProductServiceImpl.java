package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Product;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    public final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        product.setStatus(Status.NOT_AVAILABLE);
        product.setCreated(new Date());
        product.setUpdated(new Date());
        productRepository.save(product);

    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);

    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
