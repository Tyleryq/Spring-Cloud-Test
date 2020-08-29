package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {
    private final String url="http://CLOUD-PAYMENT-SERVICE";
    @Autowired       //从容器获取对象
    private RestTemplate restTemplate;
    @RequestMapping("/customer/payment/add")
    public CommonResult addPayment(Payment payment){
        return restTemplate.postForObject(url+"/payment/add",payment,CommonResult.class);
    }

    @RequestMapping("/customer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") long id){
        return restTemplate.getForObject(url+"/payment/consul/get/"+id,CommonResult.class);
    }
}
