package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProviderPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, server port: " + serverPort + "\tid: " + id;
    }
}
