package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Product;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.repository.ProductRepository;
import com.grperets.webshopv2.service.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductServiceImplUnitTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void shouldCreateProduct() {
        Product product = new Product();

        boolean isProductCreated = productService.createProduct(product);
        assertTrue(CoreMatchers.is(product.getStatus()).matches(Status.NOT_AVAILABLE));
        assertNotNull(product.getCreated());

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
        //assertTrue(CoreMatchers.is(product).matches(Mockito.doReturn(product).when(productRepository).save(product)));

    }

    @Test
    void shouldCreateProductFail() {
        Product product = new Product();
        product.setProductname("ExistingProduct");
        Mockito.doReturn(new Product()).when(productRepository).findByProductname("ExistingProduct");
        boolean isProductCreated = productService.createProduct(product);
        Mockito.verify(productRepository, Mockito.times(0)).save(ArgumentMatchers.any(Product.class));


    }
}