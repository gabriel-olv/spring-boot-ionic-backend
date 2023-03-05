package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> { }
