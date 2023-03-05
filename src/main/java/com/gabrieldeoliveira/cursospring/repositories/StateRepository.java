package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> { }
