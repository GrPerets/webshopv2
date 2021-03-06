package com.grperets.webshopv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(name = "categoryname")
    public String categoryname;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    public List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return getCategoryname().equals(category.getCategoryname()) && getProducts().equals(category.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategoryname(), getProducts());
    }
}
