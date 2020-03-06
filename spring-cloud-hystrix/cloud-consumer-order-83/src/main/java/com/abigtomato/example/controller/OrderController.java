package com.abigtomato.example.controller;

import com.abigtomato.example.client.PaymentFeignClient;
import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod") // 注册全局服务降级fallback方法
public class OrderController {

    private PaymentFeignClient paymentFeignClient;

    @Autowired
    public OrderController(PaymentFeignClient paymentFeignClient) {
        this.paymentFeignClient = paymentFeignClient;
    }

    @GetMapping("/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentFeignClient.paymentInfoOk(id);
    }

    // 设置服务调用方的降级机制，超时或报错会调用注册好的fallback方法
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
        return paymentFeignClient.paymentInfoTimeout(id);
    }

    @HystrixCommand // 声明服务降级监控，发生异常会调用全局fallback方法
    @GetMapping("/v2/timeout/{id}")
    public String paymentInfoTimeoutV2(@PathVariable("id") Integer id) {
        return paymentFeignClient.paymentInfoTimeout(id);
    }

    // 局部fallback方法
    public String paymentInfoTimeoutFallbackMethod(Integer id) {
        return "ThreadPool: " + Thread.currentThread().getName() + "\t系统繁忙或运行报错, id: " + id + "\tfail!";
    }

    // 全局fallback方法
    public String paymentGlobalFallbackMethod() {
        return "ThreadPool: " + Thread.currentThread().getName() + "\t全局服务降级处理测试";
    }
}
