package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Page<Product> getAllProducts(Pageable pageable);
    List<Product> getProductsByCategory(String category);
    List<Product> searchProducts(String name);
    List<Product> getActiveProducts();
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> filterProducts(String category, String name, Boolean active, BigDecimal minPrice, BigDecimal maxPrice);
}