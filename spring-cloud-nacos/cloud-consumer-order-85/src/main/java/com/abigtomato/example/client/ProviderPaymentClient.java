package com.abigtomato.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-payment")
public interface ProviderPaymentClient {

    @GetMapping(value = "/{id}")
    String getPayment(@PathVariable("id") Integer id);
}
