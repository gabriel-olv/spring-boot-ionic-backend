package com.gabrieldeoliveira.cursospring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public void initDb() {
        Category c1 = new Category(null, "Informática");
        Category c2 = new Category(null, "Escritório");

        categoryRepository.saveAll(Arrays.asList(c1, c2));
    }
}
