package com.test.springcloud.service;

import com.test.springcloud.entities.Payment;

public interface PaymentService {
    public int add(Payment payment);
    public Payment getPaymentById(long id);
}
