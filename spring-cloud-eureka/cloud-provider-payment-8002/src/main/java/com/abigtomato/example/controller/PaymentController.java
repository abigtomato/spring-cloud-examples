package com.abigtomato.example.controller;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.abigtomato.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
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
}
