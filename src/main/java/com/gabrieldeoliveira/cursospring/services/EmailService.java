package com.gabrieldeoliveira.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.gabrieldeoliveira.cursospring.domain.Order;

import jakarta.mail.internet.MimeMessage;

public interface EmailService {
    
    void sendOrderConfirmation(Order order);
    
    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtml(Order order);

    void sendEmailHtml(MimeMessage message);
}
