package com.abigtomato.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderPaymentMain8012 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentMain8012.class, args);
    }
}
