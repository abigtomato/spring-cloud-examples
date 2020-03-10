package com.abigtomato.example.controller;

import com.abigtomato.example.client.ProviderPaymentClient;
import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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

    @GetMapping(value = "/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",       // fallback负责处理业务类异常
            blockHandler = "blockHandler",      // blockHandler负责处理违背sentinel规则的情况
            exceptionsToIgnore = {IllegalArgumentException.class}   // 设置需要忽略的异常（不会调用fallback方法）
    )
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult<Payment> result = providerPaymentClient.paymentSQL(id);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数");
        } else if (result.getData() == null) {
            throw new NullPointerException("id无对应记录");
        }
        return result;
    }

    public CommonResult<Payment> handlerFallback(Long id, Throwable throwable) {
        return new CommonResult<>(44, "发生业务类异常调用的fallback方法", new Payment(id, null));
    }

    public CommonResult<Payment> blockHandler(Long id, BlockException exception) {
        return new CommonResult<>(44, "由sentinel限流调用的blockHandler方法", new Payment(id, null));
    }
}
