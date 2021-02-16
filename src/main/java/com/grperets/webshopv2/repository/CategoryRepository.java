package com.grperets.webshopv2.repository;

import com.grperets.webshopv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryname(String categoryname);
}
