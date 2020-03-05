package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    private RestTemplate restTemplate;

    public OrderZkController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/zk", String.class);
    }
}
