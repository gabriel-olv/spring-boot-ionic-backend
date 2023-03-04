package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> { }
