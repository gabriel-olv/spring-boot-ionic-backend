package com.gabrieldeoliveira.cursospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrieldeoliveira.cursospring.domain.BankPaymentSlip;
import com.gabrieldeoliveira.cursospring.domain.CardPayment;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(BankPaymentSlip.class);
                objectMapper.registerSubtypes(CardPayment.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
