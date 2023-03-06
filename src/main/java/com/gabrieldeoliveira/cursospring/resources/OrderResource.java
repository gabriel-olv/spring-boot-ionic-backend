package com.gabrieldeoliveira.cursospring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
    
    @Autowired
    private OrderService orderService;
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        Order findedObj = orderService.findById(id);
        return ResponseEntity.ok(findedObj);
    }
}
