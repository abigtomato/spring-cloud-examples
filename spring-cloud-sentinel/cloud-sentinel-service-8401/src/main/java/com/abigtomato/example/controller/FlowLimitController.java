package com.abigtomato.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping(value = "/a")
    public String testA() {
        return "testA";
    }

    @GetMapping(value = "b")
    public String testB() {
        return "testB";
    }
}
