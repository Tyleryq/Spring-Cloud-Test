package com.test.springcloud.dao;

import com.test.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int intsert(Payment payment);    //返回插入后的id,用于判断是否插入成功
    public Payment getPaymentById(@Param("id") long id);
}
