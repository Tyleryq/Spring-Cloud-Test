package com.test.springcloud.controller;

import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_info_ok(@PathVariable("id") Integer id){
        String re=paymentService.payment_info_ok(id);
        log.info("****result:"+re);
        return re;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_info_timeout(@PathVariable("id") Integer id){
        String re=paymentService.payment_info_timeout(id);
        log.info("****result:"+re);
        return re;
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreak(@PathVariable("id") Integer id){
        String re=paymentService.paymentCircuitBreaker(id);
        log.info("****result:"+re);
        return re;
    }
}
