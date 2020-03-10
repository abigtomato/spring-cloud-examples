package com.abigtomato.example.client;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.abigtomato.example.fallback.ProviderPaymentFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Component
@FeignClient(value = "cloud-provider-payment", fallback = ProviderPaymentFallback.class)
public interface ProviderPaymentClient {

    @GetMapping(value = "/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable(value = "id") Long id);
}
