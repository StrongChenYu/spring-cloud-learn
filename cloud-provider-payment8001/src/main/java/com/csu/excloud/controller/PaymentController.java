package com.csu.excloud.controller;


import com.csu.excloud.entities.CommonResult;
import com.csu.excloud.entities.Payment;
import com.csu.excloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Object> queryById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);

        log.info("查询payment表：" + id);

        if (payment != null) {
            return new CommonResult<>(200, "查询数据成功^v^", payment);
        } else {
            return new CommonResult<>(400, "数据不存在", null);
        }
    }


    @PostMapping("/payment/create")
    public CommonResult<Object> create(@RequestBody Payment payment) {
        int id = paymentService.create(payment);

        log.info("插入payment表：" + payment);

        if (id > 0) {
            return new CommonResult<>(200, "插入数据成功^v^", payment.getId());
        } else {
            return new CommonResult<>(200, "插入数据失败", null);
        }
    }
}
