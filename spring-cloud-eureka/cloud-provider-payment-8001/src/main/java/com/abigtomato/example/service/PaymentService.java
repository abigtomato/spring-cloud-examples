package com.abigtomato.example.service;

import com.abigtomato.example.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
