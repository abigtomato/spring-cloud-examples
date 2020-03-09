package com.abigtomato.example.controller;

import com.abigtomato.example.client.ProviderPaymentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsumerOrderController {

    private ProviderPaymentClient providerPaymentClient;

    @Autowired
    public ConsumerOrderController(ProviderPaymentClient providerPaymentClient) {
        this.providerPaymentClient = providerPaymentClient;
    }

    @GetMapping(value = "/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return providerPaymentClient.getPayment(id);
    }
}
