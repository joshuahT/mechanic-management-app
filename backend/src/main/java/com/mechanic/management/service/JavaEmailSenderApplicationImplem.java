package com.mechanic.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaEmailSenderApplicationImplem implements JavaEmailSenderApplication {

    @Autowired
    private JavaMailSender emailSender;

    private SimpleMailMessage defaultMessage;

    public JavaEmailSenderApplicationImplem(){
        defaultMessage = new SimpleMailMessage();
        defaultMessage.setFrom("mechanicsender@gmail.com");
        defaultMessage.setSubject("Mechanic Service Notification");
        defaultMessage.setText("This is an automated message from the Mechanic Management System.");
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(defaultMessage);
        message.setTo(to);

        // Override default subject and text if provided
        if (subject != null && !subject.isEmpty()) {
            message.setSubject(subject);
        }
        if (text != null && !text.isEmpty()) {
            message.setText(text);
        }

        emailSender.send(message);
    }
}
