package com.test.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "global_fallbackMethod")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/customer/payment/hystrix/ok/{id}")
    public String payment_info_ok(@PathVariable("id") Integer id){
        return paymentService.payment_info_ok(id);
    }

    @GetMapping("/customer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="1500")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="1500")
    })
    public String payment_info_timeout(@PathVariable("id") Integer id){
        return paymentService.payment_info_timeout(id);
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程名:"+Thread.currentThread().getName()+"  paymentInfo_timeoutHandler,payment系统繁忙,稍后再试,id:"+id;
    }

    public String global_fallbackMethod(){
        return "one service has fallbacked";
    }
}
