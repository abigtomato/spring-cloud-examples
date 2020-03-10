package com.abigtomato.example.controller;

import com.abigtomato.example.domain.Order;
import com.abigtomato.example.entities.CommonResult;
import com.abigtomato.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping(value = "/create")
    public CommonResult<Void> create(@RequestBody Order order) {
        orderService.create(order);
        return new CommonResult<>(200, "创建成功");
    }
}
