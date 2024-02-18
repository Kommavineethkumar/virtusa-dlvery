package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.Products;
import com.virtusa.dlvery.exceptions.ProductNotFoundException;
import com.virtusa.dlvery.repositories.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> getAllProducts() {
        LOGGER.info("Fetching all products");
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(UUID productId) {
        LOGGER.info("Fetching product with ID: {}", productId);
        return productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    @Override
    public List<Products> getProductsByProductName(String productName) {
        LOGGER.info("Fetching products by name: {}", productName);
        return productsRepository.findByProductNameContaining(productName);
    }

    @Override
    public List<Products> getProductsByCategoryId(UUID categoryId) {
        LOGGER.info("Fetching products by category ID: {}", categoryId);
        return productsRepository.findByCategory_CategoryId(categoryId);
    }

    @Override
    public Products createProduct(Products product) {
        LOGGER.info("Creating a new product");
        return productsRepository.save(product);
    }

    @Override
    public Products updateProduct(UUID productId, Products product) {
        LOGGER.info("Updating product with ID: {}", productId);
        Products existingProduct = productsRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Update product details
        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategory(product.getCategory());

        return productsRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        LOGGER.info("Deleting product with ID: {}", productId);
        productsRepository.deleteById(productId);
    }
}
