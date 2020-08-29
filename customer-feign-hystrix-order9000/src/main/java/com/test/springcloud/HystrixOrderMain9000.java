package com.test.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class HystrixOrderMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderMain9000.class,args);
    }
    /**
     *此配置是为了服务监控而配置，与服务 容错本身无关，springcloud升级后的坑
     *ServletRegistrat ionBean因为springboot的默认路径不是"/bystrix. stream",
     *只要在自己的项目里配置上下面的servlet就可以了
     * */
     @Bean
     public ServletRegistrationBean getServlet() {
         HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
         ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
         registrationBean.setLoadOnStartup(1);
         registrationBean.addUrlMappings("/hystrix.stream");
         registrationBean.setName("HystrixMetricsStreamServlet");
         return registrationBean;
     }

 }
