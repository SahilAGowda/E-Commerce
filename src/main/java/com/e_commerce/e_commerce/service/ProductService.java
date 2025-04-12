package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> searchProducts(String name);
    List<Product> getActiveProducts();
} 