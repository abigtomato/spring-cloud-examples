package com.abigtomato.example.client;

import com.abigtomato.example.fallback.PaymentFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentFeignClientFallback.class)  // 通过接口的实现类定义fallback
public interface PaymentFeignClient {

    @GetMapping("/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id);
}
