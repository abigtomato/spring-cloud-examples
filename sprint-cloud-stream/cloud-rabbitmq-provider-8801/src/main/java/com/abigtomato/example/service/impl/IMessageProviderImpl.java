package com.abigtomato.example.service.impl;

import com.abigtomato.example.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@EnableBinding(Source.class)    // 标识为output（从应用程序送出消息）
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;  // 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("serial: " + serial);
        return serial;
    }
}
