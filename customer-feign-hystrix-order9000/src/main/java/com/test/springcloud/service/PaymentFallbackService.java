package com.test.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public String payment_info_ok(Integer id) {
        return "payment ok service 暂不可用";
    }

    @Override
    public String payment_info_timeout(Integer id) {
        return "payment timeout service 暂不可用";
    }
}
