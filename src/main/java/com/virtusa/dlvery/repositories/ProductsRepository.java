package com.virtusa.dlvery.repositories;

import com.virtusa.dlvery.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID> {

    List<Products> findByProductName(String productName);

    List<Products> findByProductNameContaining(String partialProductName);

    List<Products> findByCategory_CategoryId(UUID categoryId);

    List<Products> findByCategory_CategoryName(String categoryName);

    List<Products> findByCategory_CategoryNameContaining(String partialCategoryName);

    List<Products> findByCategory_CategoryIdAndProductName(UUID categoryId, String productName);

    List<Products> findByCategory_CategoryIdAndProductNameContaining(UUID categoryId, String partialProductName);

    // Add more custom query methods as needed

}
