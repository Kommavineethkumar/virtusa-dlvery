package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, UUID> {

    List<Categories> findByCategoryName(String categoryName);

    List<Categories> findByCategoryNameContaining(String partialCategoryName);

    List<Categories> findByCategoryIdIn(List<UUID> categoryIds);

    List<Categories> findByCategoryNameAndCategoryIdNot(String categoryName, UUID categoryId);

    List<Categories> findByCategoryNameIgnoreCase(String categoryName);

    // Add more custom query methods as needed

}