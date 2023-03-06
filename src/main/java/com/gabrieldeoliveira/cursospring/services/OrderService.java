package com.gabrieldeoliveira.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.repositories.OrderRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFountException;

@Service
public class OrderService {
    
    @Autowired 
    private OrderRepository orderRepository;

    public Order findById(Integer id) {
        Order obj = orderRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFountException(
                            "Object not found ("+ Order.class.getSimpleName() +") Id: " + id
                        ));
        return obj;
    }
}
