package com.gabrieldeoliveira.cursospring.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrieldeoliveira.cursospring.domain.BankPaymentSlip;
import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.domain.OrderItem;
import com.gabrieldeoliveira.cursospring.domain.Product;
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
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BankSlipService bankSlipService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Order insert(Order obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when saving order: object was null");
        }
        obj.setId(null);
        obj.setInstant(Instant.now());
        obj.getPayment().setOrder(obj);
        obj.getPayment().setStatus(PaymentStatus.PENDING);

        Client client = clientService.findById(obj.getClient().getId());
        obj.setClient(client);

        if (obj.getPayment() instanceof BankPaymentSlip) {
            BankPaymentSlip payment = (BankPaymentSlip) obj.getPayment();
            bankSlipService.fillDueDate(payment, obj.getInstant());
        }
        obj = orderRepository.save(obj);
        obj.getClient();

        for (OrderItem x : obj.getItems()) {
            Product product = productService.findById(x.getProduct().getId());
            x.setProduct(product);
            x.setOrder(obj);
            x.setDiscount(0.0);
            x.setPrice(product.getPrice());
        }
        orderItemRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmation(obj);
        return obj;
    }

    @Transactional(readOnly = true)
    public Order findById(Integer id) {
        Order obj = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Object not found (" + Order.class.getSimpleName() + ") Id: " + id));
        return obj;
    }
}
