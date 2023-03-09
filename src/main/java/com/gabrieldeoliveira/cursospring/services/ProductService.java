package com.gabrieldeoliveira.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.Product;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;
import com.gabrieldeoliveira.cursospring.repositories.ProductRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
    
    @Autowired 
    private ProductRepository productRepository;
    
    @Autowired 
    private CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        Product obj = productRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(
                            "Object not found ("+ Product.class.getSimpleName() +") Id: " + id
                        ));
        return obj;
    }

    public Page<Product> search(String name, List<Integer> categoryIds, Pageable pageable) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        return productRepository.findByNameContainingAndCategoriesIn(name, categories, pageable);
    }
}
