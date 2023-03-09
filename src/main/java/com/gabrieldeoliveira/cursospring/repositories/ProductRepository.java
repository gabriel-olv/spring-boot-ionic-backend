package com.gabrieldeoliveira.cursospring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional(readOnly = true)
    Page<Product> findByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageable);
}
