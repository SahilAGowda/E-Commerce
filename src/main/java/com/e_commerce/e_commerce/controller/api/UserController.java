package com.e_commerce.e_commerce.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.e_commerce.e_commerce.Response;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // Login Endpoint
  @GetMapping(path = "/login")
  public Response login(
    @RequestParam String username,
    @RequestParam String password
  ) {
    Iterable<User> iterator = userRepository.findAll();

    for (User user : iterator) {
      if (
        user.getUsername().equals(username) &&
        user.getPassword().equals(password)
      ) {
        Response response = new Response();
        response.message = user.getEmail(); // Returning Email as a unique identifier
        response.status = 200;
        return response;
      }
    }

    Response response = new Response();
    response.message = "Username or Password is incorrect";
    response.status = 400;
    return response;
  }

  // Register Endpoint
  // Sample query: /register?username=johndoe&firstName=John&lastName=Doe&email=johndoe@example.com&mobileNumber=1234567890&address=New York&city=New York&state=NY&password=securepassword&aadhar=123456789012&pincode=10001&gender=male
  @GetMapping(path = "/register")
  public Response register(@ModelAttribute User user) {
    userRepository.save(user);
    Response response = new Response();
    response.message = "Registration Successful";
    response.status = 200;
    return response;
  }

  // Get User by Aadhar
  @GetMapping(path = "/get-user")
  public User getUser(@RequestParam String email) {
    return userRepository.findByEmail(email); // Fetch user by email
  }

  // Change Password Endpoint
  @GetMapping(path = "/change-password")
  public Response changePassword(
    @RequestParam String email,
    @RequestParam String password
  ) {
    User user = userRepository.findByEmail(email);
    if (user != null) {
      user.setPassword(password); // Set new password
      userRepository.save(user); // Save updated user
      Response response = new Response();
      response.message = "Password Changed Successfully";
      response.status = 200;
      return response;
    }

    Response response = new Response();
    response.message = "User not found";
    response.status = 404;
    return response;
  }

  // Get User by Email
  @GetMapping("/get-user-by-email")
  public User getUserByEmail(@RequestParam String email) {
    return userRepository.findByEmail(email); // Fetch user by email
  }
}
