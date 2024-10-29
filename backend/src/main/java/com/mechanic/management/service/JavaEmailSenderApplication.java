package com.mechanic.management.service;

public interface JavaEmailSenderApplication {
    void sendSimpleMessage(String to, String subject, String text);
}
