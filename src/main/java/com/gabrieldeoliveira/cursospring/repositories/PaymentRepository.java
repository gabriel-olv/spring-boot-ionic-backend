package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> { }
