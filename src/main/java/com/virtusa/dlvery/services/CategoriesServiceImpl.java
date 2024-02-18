package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Categories;
import com.virtusa.dlvery.exceptions.CategoryNotFoundException;
import com.virtusa.dlvery.repositories.CategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesServiceImpl.class);

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> getAllCategories() {
        LOGGER.info("Fetching all categories");
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategoryById(UUID categoryId) {
        LOGGER.info("Fetching category with ID: {}", categoryId);
        return categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + categoryId));
    }

    @Override
    public Categories createCategory(Categories category) {
        LOGGER.info("Creating a new category");
        return categoriesRepository.save(category);
    }

    @Override
    public Categories updateCategory(UUID categoryId, Categories category) {
        LOGGER.info("Updating category with ID: {}", categoryId);
        Categories existingCategory = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + categoryId));

        // Update category details
        existingCategory.setCategoryName(category.getCategoryName());
        return categoriesRepository.save(existingCategory);
    }
    @Override
    public void deleteCategory(UUID categoryId) {
        LOGGER.info("Deleting category with ID: {}", categoryId);
        categoriesRepository.deleteById(categoryId);
    }
}
