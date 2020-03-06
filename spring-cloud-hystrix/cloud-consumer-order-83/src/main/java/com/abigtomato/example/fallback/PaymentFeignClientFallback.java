package com.abigtomato.example.fallback;

import com.abigtomato.example.client.PaymentFeignClient;
import org.springframework.stereotype.Component;

/**
 * feign client接口的实现类，用于一对一实现各服务降级的fallback方法
 */
@Component
public class PaymentFeignClientFallback implements PaymentFeignClient {

    @Override
    public String paymentInfoOk(Integer id) {
        return "paymentInfoOk方法fallback测试";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "paymentInfoTimeout方法fallback测试";
    }
}
