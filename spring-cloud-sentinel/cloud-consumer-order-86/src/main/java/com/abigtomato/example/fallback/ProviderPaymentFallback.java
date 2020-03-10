package com.abigtomato.example.fallback;

import com.abigtomato.example.client.ProviderPaymentClient;
import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class ProviderPaymentFallback implements ProviderPaymentClient {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44, "由sentinel限流调用的blockHandler方法", new Payment(id, null));
    }
}
