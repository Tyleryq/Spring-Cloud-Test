package com.test.springcloud.service;

import com.test.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    public String payment_info_ok(Integer id);
    public String payment_info_timeout(Integer id);
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
