package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)  // 标识为input（接受消息到应用程序）
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT) // 消息监听
    public void input(Message<String> message) {
        log.info("consumer ---> receive message: " + message.getPayload() + "\tport: " + serverPort);
    }
}
