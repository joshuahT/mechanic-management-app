package com.mechanic.management.controller;

import com.mechanic.management.service.JavaEmailSenderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaEmailSenderApplication emailSenderService;

    // API endpoint to send an email
    @PostMapping("/send")
    public String sendEmail(
            @RequestBody String to,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String text) {

        String decodedEmail = URLDecoder.decode(to, StandardCharsets.UTF_8);

        String email = decodedEmail.replace("to=", ""); // Remove "to="

        // Send the email using the service
        emailSenderService.sendSimpleMessage(email, subject, text);

        System.out.println(email);
        return "Email sent successfully to " + to;
    }
}
