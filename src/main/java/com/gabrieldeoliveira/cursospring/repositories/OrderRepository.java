package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> { }
