package com.abigtomato.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "ThreadPool: " + Thread.currentThread().getName() + "\tpaymentInfoOk, id: " + id;
    }

    // 设置服务降级规则，指定fallback方法
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            // 设置服务响应超时的降级规则，若超时则调用fallback方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(Integer id) {
        Random random = new Random();
        random.nextInt(2);

        int timeout = 5;
//        int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ThreadPool: " + Thread.currentThread().getName() + "\tpaymentInfoTimeout, id: " + id + "\ttime: " + timeout;
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "ThreadPool: " + Thread.currentThread().getName() + "\t系统繁忙或运行报错, id: " + id + "\tfail!";
    }
}
