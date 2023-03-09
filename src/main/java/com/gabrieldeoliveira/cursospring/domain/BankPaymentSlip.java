package com.gabrieldeoliveira.cursospring.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrieldeoliveira.cursospring.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BANK_PAYMENTS_SLIP")
@JsonTypeName("bankPaymentSlip")
public class BankPaymentSlip extends Payment {
    
    private Instant dueDate;
    private Instant paymentDate;
    
    public BankPaymentSlip() {
    }

    public BankPaymentSlip(Integer id, PaymentStatus status, Order order, Instant dueDate, Instant paymentDate) {
        super(id, status, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }
}
