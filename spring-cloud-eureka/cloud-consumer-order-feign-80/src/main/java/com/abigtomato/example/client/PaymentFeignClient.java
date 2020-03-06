package com.abigtomato.example.client;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")   // 声明openfeign客户端，指定调用的服务名
public interface PaymentFeignClient {

    @GetMapping(value = "/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);  // 声明调用的远程服务的api

    @GetMapping(value = "/timeout")
    String paymentFeignTimeout();
}
