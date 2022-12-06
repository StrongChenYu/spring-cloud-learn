package com.csu.excloud.controller;


import com.csu.excloud.entities.CommonResult;
import com.csu.excloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@EnableDiscoveryClient
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT";

    @Resource
    RestTemplate restTemplate;

    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/order/payment/get/{id}")
    public CommonResult queryById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @PostMapping("/order/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/service/info")
    public Object getServiceInfo() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service list: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId());
            log.info(instance.getHost());
            log.info(String.valueOf(instance.getPort()));
            log.info(String.valueOf(instance.getUri()));
        }

        return discoveryClient;
    }
}
