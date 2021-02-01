package com.grperets.webshopv2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grperets.webshopv2.model.Category;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Long id;
    private String categoryname;

    public Category toCategory(){
        Category category = new Category();
        category.setId(this.id);
        category.setCategoryname(this.categoryname);
        return category;

    }

    public static CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setCategoryname(category.getCategoryname());
        return categoryDTO;
    }
}
