package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { }
