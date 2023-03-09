package com.gabrieldeoliveira.cursospring.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Order order) {
        Order insertedObj = orderService.insert(order);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(insertedObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        Order findedObj = orderService.findById(id);
        return ResponseEntity.ok(findedObj);
    }
}
