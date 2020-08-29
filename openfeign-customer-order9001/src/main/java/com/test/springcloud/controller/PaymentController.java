package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/customer/feign/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/customer/feign/payment/timeout")
    public String timeOut(){
        return paymentService.timeOut();
    }
}
