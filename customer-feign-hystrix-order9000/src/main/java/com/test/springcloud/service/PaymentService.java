package com.test.springcloud.service;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFallbackService.class)   //要调用的服务名
public interface PaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_info_ok(@PathVariable("id") Integer id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_info_timeout(@PathVariable("id") Integer id);
}
