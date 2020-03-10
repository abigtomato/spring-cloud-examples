package com.abigtomato.example.controller;

import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.abigtomato.example.handler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按资源名称限流测试", new Payment(2020L, "serial2020"));
    }
    public CommonResult<String> handlerException(BlockException exception) {
        return new CommonResult<>(444, "服务不可用", exception.getClass().getCanonicalName());
    }

    @GetMapping(value = "/rateLimit/byUrl")
    @SentinelResource(value = "byUrl", blockHandler = "handlerException")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "按url限流测试", new Payment(2020L, "serial2020"));
    }

    @GetMapping(value = "/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerExceptionV2")
    public CommonResult<Payment> customerBlockHandler() {
        return new CommonResult<>(200, "按用户自定义规则限流测试", new Payment(2020L, "serial2020"));
    }
}
