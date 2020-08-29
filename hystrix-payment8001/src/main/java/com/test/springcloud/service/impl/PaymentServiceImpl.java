package com.test.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String payment_info_ok(Integer id) {
        return "线程名:"+Thread.currentThread().getName()+"  payment_ok,id:"+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="5000")
    })
    public String payment_info_timeout(Integer id) {
//        int num=1/0;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名:"+Thread.currentThread().getName()+"  payment_timeout,id:"+id;
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程名:"+Thread.currentThread().getName()+"  paymentInfo_timeoutHandler,稍后再试,id:"+id;
    }

    //====服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),// 失败率达到多少后跳闸
    })  //在10s内请求10次失败率为60%则熔断
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0)
            throw new RuntimeException("******id不能负数");
        String serialNumber = UUID.randomUUID().toString();
        return Thread. currentThread(). getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能负数，请稍后再试，/(ToT)/~~ id: " +id;
    }

}
