package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    boolean createCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(Long id);
    List<Category> getAllCategories();
}
