package com.grperets.webshopv2.controller;

import com.grperets.webshopv2.dto.CategoryDTO;
import com.grperets.webshopv2.model.Category;
import com.grperets.webshopv2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryRestControllerV1 {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestControllerV1(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.getById(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryDTO categoryDTO  = CategoryDTO.fromCategory(category);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        if (categoryDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.categoryService.create(categoryDTO.toCategory());
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        if(categoryDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.categoryService.update(categoryDTO.toCategory());
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long id ){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category category = this.categoryService.getById(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.categoryService.delete(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<Category> categories = this.categoryService.getAll();
        if(categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category: categories){
            categoryDTOS.add(CategoryDTO.fromCategory(category));
        }
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

}
