package com.grperets.webshopv2.controller;

import com.grperets.webshopv2.dto.ProductDTO;
import com.grperets.webshopv2.model.Product;
import com.grperets.webshopv2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductRestControllerV1 {

    private final ProductService productService;

    @Autowired
    public ProductRestControllerV1(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id){
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = this.productService.getById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDTO productDTO = ProductDTO.fromProduct(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO){
        if (productDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productDTO.toProduct();

        this.productService.create(product);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);

    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO){
        if(productDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productDTO.toProduct();
        this.productService.update(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);


    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id){
        Product product = this.productService.getById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.productService.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts(){

        List<Product> products = this.productService.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product: products){
            productDTOS.add(ProductDTO.fromProduct(product));
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);

    }
}
