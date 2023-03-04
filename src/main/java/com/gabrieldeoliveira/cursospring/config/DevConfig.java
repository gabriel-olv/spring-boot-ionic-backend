package com.gabrieldeoliveira.cursospring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.Product;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;
import com.gabrieldeoliveira.cursospring.repositories.ProductRepository;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired ProductRepository productRepository;

    @Bean
    public void initDb() {
        Category c1 = new Category(null, "Informática");
        Category c2 = new Category(null, "Escritório");
        
        Product p1 = new Product(null, "Computador", 3000.0);
        Product p2 = new Product(null, "Impressora", 800.0);
        Product p3 = new Product(null, "Mouse", 80.0);
        
        c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        c2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(c1));
        p2.getCategories().addAll(Arrays.asList(c1, c2));
        p3.getCategories().addAll(Arrays.asList(c1));
        
        categoryRepository.saveAll(Arrays.asList(c1, c2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
