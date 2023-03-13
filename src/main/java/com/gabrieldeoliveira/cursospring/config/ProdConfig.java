package com.gabrieldeoliveira.cursospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.services.EmailService;
import com.gabrieldeoliveira.cursospring.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    EmailService emailService() {
        return new SmtpEmailService();
    }
}
