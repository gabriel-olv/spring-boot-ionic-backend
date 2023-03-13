package com.gabrieldeoliveira.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import com.gabrieldeoliveira.cursospring.domain.Order;

public interface EmailService {
    
    void sendOrderConfirmation(Order order);
    
    void sendEmail(SimpleMailMessage message);
}
