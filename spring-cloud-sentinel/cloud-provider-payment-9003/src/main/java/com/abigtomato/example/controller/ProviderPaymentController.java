package com.abigtomato.example.controller;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ProviderPaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static Map<Long, Payment> map = new HashMap<>();

    static {
        map.put(1L, new Payment(1L, "hadoop"));
        map.put(2L, new Payment(2L, "spark"));
        map.put(3L, new Payment(3L, "flink"));
    }

    @GetMapping(value = "/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable(value = "id") Long id) {
        Payment payment = map.get(id);
        return new CommonResult<>(200, "from mysql, serverPort: " + serverPort, payment);
    }
}
