package com.gabrieldeoliveira.cursospring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Sending email...");
        mailSender.send(message);
        LOGGER.info("Email sended!");
    }

    @Override
    public void sendEmailHtml(MimeMessage message) {
        LOGGER.info("Sending email...");
        javaMailSender.send(message);
        LOGGER.info("Email sended!");
    }
}