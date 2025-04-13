package com.e_commerce.e_commerce.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.e_commerce.e_commerce.Response;

@RestController
@RequestMapping("/api")
public class AdminLoginController {

    // Typically, these would be fetched from a database
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123"; // Example of a more secure password

    @GetMapping("/admin-login")
    public Response adminLogin(
            @RequestParam String username,
            @RequestParam String password
    ) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            Response response = new Response();
            response.setMessage("Login Successful");
            response.setStatus(200); // HTTP Status 200 for success
            return response;
        } else {
            Response response = new Response();
            response.setMessage("Username or Password is incorrect");
            response.setStatus(400); // HTTP Status 400 for bad request
            return response;
        }
    }
}
