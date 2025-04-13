package com.e_commerce.e_commerce.controller.api;

import com.e_commerce.e_commerce.entity.VerifyUser;
import com.e_commerce.e_commerce.repository.VerifyUserRepository;
import com.e_commerce.e_commerce.EmailSenderService;
import com.e_commerce.e_commerce.Response;  // Assuming you have a Response class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

    @Autowired
    private VerifyUserRepository verifyUserRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    // Endpoint to send OTP to the user's email
    @GetMapping("/send-otp")
    public Response sendOtp(@RequestParam String email) {
        String OTP = (int) Math.floor(100000 + Math.random() * 9000000) + "";  // Generate OTP
        emailSenderService.sendEmail(email, "OTP for E-Commerce App", "Your OTP is " + OTP);  // Send OTP email
        
        // Save the OTP and email to the repository
        VerifyUser verifyUser = new VerifyUser();
        verifyUser.setEmail(email);
        verifyUser.setOtp(OTP);
        verifyUserRepository.save(verifyUser);
        
        // Prepare the response
        Response response = new Response();
        response.setMessage("OTP Sent Successfully");
        response.setStatus(200);
        return response;
    }

    // Endpoint to verify the OTP entered by the user
    @GetMapping("/verify-otp")
    @Transactional
    public Response verifyOtp(@RequestParam String email, @RequestParam String otp) {
        VerifyUser verifyUser = verifyUserRepository.findByEmail(email);  // Fetch the user by email
        
        // Compare the entered OTP with the one stored in the database
        if (verifyUser != null && verifyUser.getOtp().equals(otp)) {
            verifyUserRepository.deleteByEmail(email);  // OTP verified, delete it from the database
            
            // Prepare successful response
            Response response = new Response();
            response.setMessage("OTP Verified Successfully");
            response.setStatus(200);
            return response;
        }
        
        // Prepare failure response
        Response response = new Response();
        response.setMessage("OTP Verification Failed");
        response.setStatus(400);
        return response;
    }
}
