package com.gabrieldeoliveira.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldeoliveira.cursospring.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> { }
