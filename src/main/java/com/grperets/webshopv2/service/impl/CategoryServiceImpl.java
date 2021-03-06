package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Category;
import com.grperets.webshopv2.repository.CategoryRepository;
import com.grperets.webshopv2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createCategory(Category category) {
        if (this.categoryRepository.findByCategoryname(category.categoryname) != null){
            return false;
        }
        category.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        if (this.categoryRepository.save(category) != null){
            return true;
        }
        return false;

    }

    @Override
    public boolean updateCategory(Category category){
        Category existingСategory = this.categoryRepository.findById(category.getId()).orElse(new Category());
        //existingСategory.setId(category.getId());
        existingСategory.setCategoryname(category.getCategoryname());
        existingСategory.setUpdated(Timestamp.valueOf(LocalDateTime.now()));

        if (this.categoryRepository.save(existingСategory) != null){
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteCategory(Long id) {
        Category category = getCategoryById(id);
        if (category != null){
            this.categoryRepository.delete(category);
            return true;
        }
        return false;

    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}
