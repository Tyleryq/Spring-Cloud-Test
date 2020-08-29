package com.test.springcloud.service.impl;

import com.test.springcloud.dao.PaymentDao;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int add(Payment payment) {
        return paymentDao.intsert(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
