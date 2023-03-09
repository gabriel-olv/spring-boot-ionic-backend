package com.gabrieldeoliveira.cursospring.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrieldeoliveira.cursospring.domain.BankPaymentSlip;
import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.domain.OrderItem;
import com.gabrieldeoliveira.cursospring.domain.enums.PaymentStatus;
import com.gabrieldeoliveira.cursospring.repositories.OrderItemRepository;
import com.gabrieldeoliveira.cursospring.repositories.OrderRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BankSlipService bankSlipService;

    @Transactional
    public Order insert(Order obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when saving order: object was null");
        }
        obj.setId(null);
        obj.setInstant(Instant.now());
        obj.getPayment().setOrder(obj);
        obj.getPayment().setStatus(PaymentStatus.PENDING);

        if (obj.getPayment() instanceof BankPaymentSlip) {
            BankPaymentSlip payment = (BankPaymentSlip) obj.getPayment();
            bankSlipService.fillDueDate(payment, obj.getInstant());
        }
        obj = orderRepository.save(obj);

        for (OrderItem x : obj.getItems()) {
            Double price = productService.findById(x.getProduct().getId()).getPrice();
            x.setOrder(obj);
            x.setDiscount(0.0);
            x.setPrice(price);
        }
        orderItemRepository.saveAll(obj.getItems());
        return obj;
    }

    public Order findById(Integer id) {
        Order obj = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Object not found (" + Order.class.getSimpleName() + ") Id: " + id));
        return obj;
    }
}
