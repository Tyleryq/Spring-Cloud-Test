package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeignOrderMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeignOrderMain9001.class,args);
    }
}
