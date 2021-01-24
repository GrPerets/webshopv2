package com.grperets.webshopv2.product.service;

import com.grperets.webshopv2.product.model.Product;
import com.grperets.webshopv2.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productRepository.getOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);

    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
