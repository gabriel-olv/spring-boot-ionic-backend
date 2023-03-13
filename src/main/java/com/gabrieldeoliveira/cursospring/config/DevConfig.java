package com.gabrieldeoliveira.cursospring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.services.DBService;
import com.gabrieldeoliveira.cursospring.services.EmailService;
import com.gabrieldeoliveira.cursospring.services.MockEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Autowired
    private DBService dbService;

    @Bean
    public void initDb() {
        if (strategy.equals("create") || strategy.equals("create-drop"))
            dbService.initDb();
    }

    @Bean
    EmailService emailService() {
        return new MockEmailService();
    }
}
