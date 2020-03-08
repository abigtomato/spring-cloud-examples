package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope   // config配置动态刷新（修改配置后需要发送一次post请求才能动态刷新，请求地址在README）
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config")
    public String getConfigInfo() {
        return serverPort + "\t" + configInfo;
    }
}
