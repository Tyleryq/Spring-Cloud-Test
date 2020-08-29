package com.test.springcloud.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.test.springcloud.entities.CommonResult;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")   //获取配置文件中的值
    private String port;
    @Autowired
    private DiscoveryClient discoveryClient;
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
            return new CommonResult(444,"没有查到,ID:"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services=discoveryClient.getServices();    //获取Eureka server中的服务
        for(String element:services){
            log.info(element);
        }
        //根据服务名获取实例
        List<ServiceInstance> instances=discoveryClient.getInstances("cloud-payment-service");
        for(ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String timeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/payment/port")
    public String port(){
        return port;
    }

}
