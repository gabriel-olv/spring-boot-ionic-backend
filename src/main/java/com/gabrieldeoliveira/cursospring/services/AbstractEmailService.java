package com.gabrieldeoliveira.cursospring.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.gabrieldeoliveira.cursospring.domain.Order;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${email.default.sender}")
    private String senderEmail;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    @Override
    public void sendOrderConfirmationHtml(Order order) {
        try {
            MimeMessage message = prepareMimeMessageFrom(order);
            sendEmailHtml(message);
        } catch (MessagingException e) {
            sendOrderConfirmation(order);
        }
    }


    protected MimeMessage prepareMimeMessageFrom(Order obj) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(senderEmail);
        mmh.setSubject("Confirmação de pedido!");
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlTemplateFrom(obj), true);
        return mm;
    }

    protected String htmlTemplateFrom(Order obj) {
        Context context = new Context();
        context.setVariable("order", obj);
        return templateEngine.process("email/order-confirmation", context);
    }
}
