package com.csu.excloud.dao;


import com.csu.excloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);
    Payment queryById(@Param("id") Long id);
}
