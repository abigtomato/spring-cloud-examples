package com.abigtomato.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderMain8010 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8010.class, args);
    }
}
