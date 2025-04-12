package com.e_commerce.e_commerce;

import com.e_commerce.e_commerce.entity.Product;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.service.ProductService;
import com.e_commerce.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public MainController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    // 1. Authentication & User Pages

    @GetMapping("/user-register")
    public String userRegisterPage() {
        return "user-register-page.html";
    }

    @GetMapping("/user-login")
    public String userLoginPage() {
        return "user-login-page.html";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password-page.html";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage() {
        return "reset-password-page.html";
    }

    @GetMapping("/user-dashboard")
    public String userDashboardPage(Model model) {
        // In a real application, you would get the current user from the session
        // For now, we'll just pass all users
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-dashboard-page.html";
    }

    // 2. Shopping Pages

    @GetMapping("/")
    public String mainPage(Model model) {
        // Get featured products for the homepage
        List<Product> featuredProducts = productService.getActiveProducts();
        model.addAttribute("featuredProducts", featuredProducts);
        return "main-page.html";
    }

    @GetMapping("/product-list")
    public String productListPage(Model model) {
        // Get all products for the product list page
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list-page.html";
    }

    @GetMapping("/product-details/{id}")
    public String productDetailsPage(@PathVariable Long id, Model model) {
        // Get product details by ID
        productService.getProductById(id).ifPresent(product -> 
            model.addAttribute("product", product)
        );
        return "product-details-page.html";
    }

    @GetMapping("/search-products")
    public String searchProductsPage(@RequestParam(required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            // Search products by name
            List<Product> searchResults = productService.searchProducts(query);
            model.addAttribute("searchResults", searchResults);
            model.addAttribute("query", query);
        }
        return "search-products-page.html";
    }

    @GetMapping("/cart")
    public String cartPage() {
        // Cart functionality would be implemented with session management
        return "cart-page.html";
    }

    @GetMapping("/checkout")
    public String checkoutPage() {
        // Checkout functionality would be implemented with session management
        return "checkout-page.html";
    }

    @GetMapping("/payment")
    public String paymentPage() {
        // Payment functionality would be implemented with session management
        return "payment-page.html";
    }

    @GetMapping("/order-confirmation")
    public String orderConfirmationPage() {
        // Order confirmation functionality would be implemented with session management
        return "order-confirmation-page.html";
    }

    // 3. Admin Pages

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login-page.html";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboardPage(Model model) {
        // Get statistics for admin dashboard
        List<Product> products = productService.getAllProducts();
        List<User> users = userService.getAllUsers();
        
        model.addAttribute("totalProducts", products.size());
        model.addAttribute("totalUsers", users.size());
        model.addAttribute("recentProducts", products.subList(0, Math.min(5, products.size())));
        
        return "admin-dashboard-page.html";
    }

    @GetMapping("/add-product")
    public String addProductPage() {
        return "add-product-page.html";
    }

    @GetMapping("/edit-product/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        // Get product by ID for editing
        productService.getProductById(id).ifPresent(product -> 
            model.addAttribute("product", product)
        );
        return "edit-product-page.html";
    }

    @GetMapping("/delete-product")
    public String deleteProductPage(Model model) {
        // Get all products for deletion
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "delete-product-page.html";
    }

    @GetMapping("/manage-orders")
    public String manageOrdersPage(Model model) {
        // In a real application, you would get orders from the OrderService
        // For now, we'll just pass a message
        model.addAttribute("message", "Order management functionality will be implemented soon.");
        return "manage-orders-page.html";
    }

    @GetMapping("/manage-users")
    public String manageUsersPage(Model model) {
        // Get all users for management
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "manage-users-page.html";
    }
}
