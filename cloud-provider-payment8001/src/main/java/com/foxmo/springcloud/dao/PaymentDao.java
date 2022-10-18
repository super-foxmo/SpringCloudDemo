package com.foxmo.springcloud.dao;

import com.foxmo.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int createPayment(Payment payment);

    public Payment  getPaymentById(@Param("id") Long id);
}
