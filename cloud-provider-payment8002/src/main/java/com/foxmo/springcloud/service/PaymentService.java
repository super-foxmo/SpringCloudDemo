package com.foxmo.springcloud.service;

import com.foxmo.springcloud.entities.Payment;

public interface PaymentService {
    public int createPayment(Payment payment);

    public Payment  getPaymentById(Long id);
}
