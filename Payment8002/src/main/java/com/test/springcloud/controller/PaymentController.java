package com.test.springcloud.controller;

import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;
    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        log.info("插入结果:"+result);
        if(result>0){
            return new CommonResult(200,"增加成功",result);
        } else {
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果:"+result);
        if(result!=null){
            return new CommonResult(200,"查询成功,port:"+port,result);
        } else {
            return new CommonResult(444, "没有查到,ID:" + id, null);
        }
    }

    @GetMapping("/payment/port")
    public String port(){
        return port;
    }
}
