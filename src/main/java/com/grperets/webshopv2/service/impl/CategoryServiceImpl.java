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
    public Category getById(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Category category) {
        category.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        this.categoryRepository.save(category);

    }

    @Override
    public void update(Category category){
        Category existingСategory = this.categoryRepository.findById(category.getId()).orElse(new Category());
        //existingСategory.setId(category.getId());
        existingСategory.setCategoryname(category.getCategoryname());
        existingСategory.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        this.categoryRepository.save(existingСategory);
    }


    @Override
    public void delete(Category category) {
        this.categoryRepository.delete(category);

    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
