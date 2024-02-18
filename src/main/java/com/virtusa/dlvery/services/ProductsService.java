package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Products;

import java.util.List;
import java.util.UUID;

public interface ProductsService {
    List<Products> getAllProducts();
    Products getProductById(UUID productId);
    List<Products> getProductsByProductName(String productName);
    List<Products> getProductsByCategoryId(UUID categoryId);
    Products createProduct(Products product);
    Products updateProduct(UUID productId, Products product);
    void deleteProduct(UUID productId);
}