package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.Category;
import com.grperets.webshopv2.model.Product;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private Long id;
    private String productname;
    private String category;
    private BigDecimal price;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setProductname(this.productname);
        Category category = new Category();
        category.setCategoryname(this.category);
        product.setCategory(category);
        product.setPrice(this.price);
        return product;
    }

    public static ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductname(product.getProductname());
        productDTO.setCategory(product.getCategory().categoryname);
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
