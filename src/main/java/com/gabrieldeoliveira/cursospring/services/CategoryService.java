package com.gabrieldeoliveira.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired 
    private CategoryRepository categoryRepository;

    public Category insert(Category obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when saving category: object was null");
        }
        return categoryRepository.save(obj);
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }
}
