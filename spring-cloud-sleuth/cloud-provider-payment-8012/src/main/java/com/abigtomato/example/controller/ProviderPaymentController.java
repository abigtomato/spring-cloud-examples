package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProviderPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/port")
    public String getServerPort() {
        return serverPort;
    }
}
