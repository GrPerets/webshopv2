package com.grperets.webshopv2.service;

import com.grperets.webshopv2.model.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);
    void save(Category category);
    void delete(Category category);
    List<Category> getAll();
}
