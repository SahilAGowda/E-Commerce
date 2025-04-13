package com.e_commerce.e_commerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Authentication & User Pages
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
    public String userDashboardPage() {
        return "user-dashboard-page.html";
    }

    // Shopping Pages
    @GetMapping("/")
    public String mainPage() {
        return "main-page.html";
    }

    @GetMapping("/product-list")
    public String productListPage() {
        return "product-list-page.html";
    }

    @GetMapping("/product-details")
    public String productDetailsPage() {
        return "product-details-page.html";
    }

    @GetMapping("/search-products")
    public String searchProductsPage() {
        return "search-products-page.html";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart-page.html";
    }

    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout-page.html";
    }

    @GetMapping("/payment")
    public String paymentPage() {
        return "payment-page.html";
    }

    @GetMapping("/order-confirmation")
    public String orderConfirmationPage() {
        return "order-confirmation-page.html";
    }

    // Admin Pages
    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login-page.html";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboardPage() {
        return "admin-dashboard-page.html";
    }

    @GetMapping("/add-product")
    public String addProductPage() {
        return "add-product-page.html";
    }

    @GetMapping("/edit-product")
    public String editProductPage() {
        return "edit-product-page.html";
    }

    @GetMapping("/delete-product")
    public String deleteProductPage() {
        return "delete-product-page.html";
    }

    @GetMapping("/manage-orders")
    public String manageOrdersPage() {
        return "manage-orders-page.html";
    }

    @GetMapping("/manage-users")
    public String manageUsersPage() {
        return "manage-users-page.html";
    }
}
