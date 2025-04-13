package com.e_commerce.e_commerce.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.e_commerce.e_commerce.Response;
import com.e_commerce.e_commerce.entity.Product;
import com.e_commerce.e_commerce.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // QUERY
    // http://localhost:8080/api/create-product?productName=product1&price=100&category=electronics&description=SomeDescription
    @GetMapping("/create-product")
    public Response createProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        Response response = new Response();
        response.message = "Product Created Successfully";
        response.status = 200;
        return response;
    }

    // QUERY
    // http://localhost:8080/api/edit-product?productId=1234&productName=productUpdated&price=150&category=electronics
    @GetMapping("/edit-product")
    public Response editProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        Response response = new Response();
        response.message = "Product Edited Successfully";
        response.status = 200;
        return response;
    }

    // QUERY
    // http://localhost:8080/api/delete-product?productId=1234
    @GetMapping("/delete-product")
    public Response deleteProduct(@RequestParam String productId) {
        productRepository.deleteById(productId);
        Response response = new Response();
        response.message = "Product Deleted Successfully";
        response.status = 200;
        return response;
    }

    // QUERY
    // http://localhost:8080/api/all-products
    @GetMapping("/all-products")
    public Iterable<Product> allProducts() {
        return productRepository.findAll();
    }

    // QUERY
    // http://localhost:8080/api/search-product-by-id?productId=1234
    @GetMapping("/search-product-by-id")
    public Product searchProductById(@RequestParam String productId) {
        return productRepository.findById(productId).get();
    }

    // QUERY
    // http://localhost:8080/api/search-product?name=product1&category=electronics
    @GetMapping("/search-product")
    public Iterable<Product> searchProduct(
        @RequestParam String name,
        @RequestParam String category
    ) {
        return productRepository.findAllByCategoryAndBrand(name, category);
    }
}
