package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Categories;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {
    List<Categories> getAllCategories();
    Categories getCategoryById(UUID categoryId);
    Categories createCategory(Categories category);
    Categories updateCategory(UUID categoryId, Categories category);
    void deleteCategory(UUID categoryId);
}
