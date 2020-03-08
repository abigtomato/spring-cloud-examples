package com.abigtomato.example.controller;

import com.abigtomato.example.client.PaymentFeignClient;
import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    private PaymentFeignClient paymentFeignClient;

    @Autowired
    public OrderController(PaymentFeignClient paymentFeignClient) {
        this.paymentFeignClient = paymentFeignClient;
    }

    @GetMapping(value = "/port")
    public String getServerPort() {
        return paymentFeignClient.getServerPort();
    }
}
