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
import java.util.stream.Collectors;


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
        Product product = this.productService.getProductById(id);
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

        if (this.productService.createProduct(product)){
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);


    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO){
        if(productDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productDTO.toProduct();
        if (this.productService.updateProduct(product)){
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id){

        if (this.productService.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts(){

        List<Product> products = this.productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProductDTO> productDTOS = products.stream().map(product -> ProductDTO.fromProduct(product)).collect(Collectors.toList());

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);

    }
}
