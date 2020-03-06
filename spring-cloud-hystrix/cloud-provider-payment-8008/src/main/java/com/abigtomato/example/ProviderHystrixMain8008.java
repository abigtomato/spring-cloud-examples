package com.abigtomato.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker   // 开启hystrix保护机制
public class ProviderHystrixMain8008 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixMain8008.class, args);
    }
}
