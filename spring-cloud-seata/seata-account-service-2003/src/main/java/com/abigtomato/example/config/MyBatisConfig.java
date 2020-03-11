package com.abigtomato.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.abigtomato.example.dao"})
public class MyBatisConfig {
}
