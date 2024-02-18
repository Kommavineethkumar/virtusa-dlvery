package com.virtusa.dlvery.controllers;

import com.virtusa.dlvery.entities.Products;
import com.virtusa.dlvery.exceptions.ProductNotFoundException;
import com.virtusa.dlvery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> productsList = productsService.getAllProducts();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable UUID productId) {
        try {
            Products product = productsService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<List<Products>> getProductsByProductName(@PathVariable String productName) {
        List<Products> productsList = productsService.getProductsByProductName(productName);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Products>> getProductsByCategoryId(@PathVariable UUID categoryId) {
        List<Products> productsList = productsService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        try {
            Products createdProduct = productsService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Products> updateProduct(@PathVariable UUID productId, @RequestBody Products product) {
        try {
            Products updatedProduct = productsService.updateProduct(productId, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        try {
            productsService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
