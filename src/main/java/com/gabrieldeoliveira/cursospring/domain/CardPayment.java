package com.gabrieldeoliveira.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrieldeoliveira.cursospring.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARD_PAYMENTS")
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    
    private Integer installments;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentStatus status, Order order, Integer installments) {
        super(id, status, order);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
