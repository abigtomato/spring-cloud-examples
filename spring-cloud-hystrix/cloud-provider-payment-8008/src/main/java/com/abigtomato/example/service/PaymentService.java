package com.abigtomato.example.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "ThreadPool: " + Thread.currentThread().getName() + "\tpaymentInfoOk, id: " + id;
    }

    /**
     * 服务降级案例
     */
    // 设置服务降级规则，指定fallback方法
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            // 设置服务响应超时的降级规则，若超时则调用fallback方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(Integer id) {
        Random random = new Random();
        random.nextInt(2);

        int timeout = 5;
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

    /**
     * 服务熔断案例
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            // 配置监控10次请求内的失败率达60%则断路器进入开启状态，此时针对任何请求都是直接调用fallback方法
            // 经过一定的时间窗口，断路器进入半开状态，尝试接受请求，成功则关闭，若仍然失败，则继续开启断路器
            // 重复等待时间窗口，尝试接受请求的过程
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 启用断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   // 时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),   // 失败率
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "success, uuid: " + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数，请重试，id: " + id;
    }
}
