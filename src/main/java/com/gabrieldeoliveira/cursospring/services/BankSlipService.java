package com.gabrieldeoliveira.cursospring.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.BankPaymentSlip;

@Service
public class BankSlipService {
    
    public void fillDueDate(BankPaymentSlip payment, Instant orderInstant) {
        Instant dueDate = orderInstant.plus(7, ChronoUnit.DAYS);
        payment.setDueDate(dueDate);
    }
}
