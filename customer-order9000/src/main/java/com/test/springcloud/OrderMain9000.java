package com.test.springcloud;

import com.test.loadbalancerule.LoadRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = LoadRule.class)
public class OrderMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain9000.class,args);
    }
}
