package com.abigtomato.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderPaymentMain9002 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentMain9002.class, args);
    }
}
