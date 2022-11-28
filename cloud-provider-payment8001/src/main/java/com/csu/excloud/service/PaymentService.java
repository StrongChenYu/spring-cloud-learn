package com.csu.excloud.service;


import com.csu.excloud.dao.PaymentDao;
import com.csu.excloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface PaymentService {
    int create(Payment payment);
    Payment queryById(Long id);
}
