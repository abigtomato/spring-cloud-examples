package com.abigtomato.example.controller;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.abigtomato.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    // 服务发现客户端
    private DiscoveryClient discoveryClient;

    @Autowired
    public PaymentController(PaymentService paymentService,
                             DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("----> create 操作结果: " + result);
        if (result > 0) {
            return new CommonResult<>(200, "insert success, " + serverPort, result);
        } else {
            return new CommonResult<>(444, "insert fail, " + serverPort, null);
        }
    }

    @GetMapping(value = "/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("----> getById 查询结果: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功, " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应结果, " + serverPort, null);
        }
    }

    @GetMapping(value = "/discover")
    public Object discover() {
        // 获取服务列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("----> service: " + service);
        }

        // 获取指定服务的实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("----> instance: " + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }
}
