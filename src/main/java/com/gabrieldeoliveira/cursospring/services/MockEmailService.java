package com.gabrieldeoliveira.cursospring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import jakarta.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);



    @Override
    public void sendEmail(SimpleMailMessage message) {        
        LOGGER.info("Sending email...");
        LOGGER.info(message.toString());
        LOGGER.info("Email sended!");
    }

    @Override
    public void sendEmailHtml(MimeMessage message) {
        LOGGER.info("Sending email html...");
        LOGGER.info(message.toString());
        LOGGER.info("Email sended!");
    }
}
