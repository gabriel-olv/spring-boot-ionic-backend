package com.gabrieldeoliveira.cursospring.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.gabrieldeoliveira.cursospring.domain.Order;

public abstract class AbstractEmailService implements EmailService {

    @Value("${email.default.sender}")
    private String senderEmail;

    @Override
    public void sendOrderConfirmation(Order order) {
        SimpleMailMessage message = prepareSimpleMailMessageFrom(order);
        sendEmail(message);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFrom(Order obj) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(obj.getClient().getEmail());
        smm.setFrom(senderEmail);
        smm.setSubject("Confirmação de pedido! - Pedido nº.: " + obj.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(obj.toString());
        return smm;
    }
}
