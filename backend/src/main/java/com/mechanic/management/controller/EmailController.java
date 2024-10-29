package com.mechanic.management.controller;

import com.mechanic.management.service.JavaEmailSenderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaEmailSenderApplication emailSenderService;

    // API endpoint to send an email
    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String text) {

        // Send the email using the service
        emailSenderService.sendSimpleMessage(to, subject, text);

        return "Email sent successfully to " + to;
    }
}
