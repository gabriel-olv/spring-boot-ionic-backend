package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> { }
