package com.abigtomato.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/a")
    public String testA() {
        return "testA";
    }

    @GetMapping(value = "b")
    public String testB() {
        return "testB";
    }

    @GetMapping(value = "c")
    public String testC() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testC 测试RT");
        return "testC";
    }

    @GetMapping(value = "d")
    public String testD() {
        log.info("testD 测试异常比例和异常数");
        int age = 10 / 0;
        return "testC";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")   // 定义sentinel资源，fallback方法只会在符合sentinel控制规则的时候触发
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        log.info("testHotKey 测试热点参数");
        return "testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "deal_testHotKey";
    }
}
