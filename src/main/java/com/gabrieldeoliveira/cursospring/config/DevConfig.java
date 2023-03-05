package com.gabrieldeoliveira.cursospring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.City;
import com.gabrieldeoliveira.cursospring.domain.Product;
import com.gabrieldeoliveira.cursospring.domain.State;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;
import com.gabrieldeoliveira.cursospring.repositories.CityRepository;
import com.gabrieldeoliveira.cursospring.repositories.ProductRepository;
import com.gabrieldeoliveira.cursospring.repositories.StateRepository;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired 
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private CityRepository cityRepository;


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

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São Paulo");

        City ct1 = new City(null, "Uberlândia", st1);
        City ct2 = new City(null, "São Paulo", st2);
        City ct3 = new City(null, "Campinas", st2);

        st1.getCities().addAll(Arrays.asList(ct1));
        st2.getCities().addAll(Arrays.asList(ct2, ct3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(ct1, ct2, ct3));
    }
}
