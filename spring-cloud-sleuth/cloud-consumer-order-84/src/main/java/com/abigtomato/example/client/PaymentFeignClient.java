package com.abigtomato.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "cloud-provider-payment")
public interface PaymentFeignClient {

    @GetMapping(value = "/port")
    String getServerPort();
}
