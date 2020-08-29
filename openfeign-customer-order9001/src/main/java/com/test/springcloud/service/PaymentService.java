package com.test.springcloud.service;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")   //要调用的服务名
public interface PaymentService {
    @GetMapping("/payment/get/{id}")   //调用的地址
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id);
    @GetMapping("/payment/timeout")
    public String timeOut();
}
