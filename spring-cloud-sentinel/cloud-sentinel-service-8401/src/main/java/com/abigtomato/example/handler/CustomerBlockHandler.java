package com.abigtomato.example.handler;

import com.abigtomato.example.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public CommonResult<String> handlerException(BlockException exception) {
        return new CommonResult<>(444, "服务不可用", exception.getClass().getCanonicalName());
    }

    public CommonResult<String> handlerExceptionV2(BlockException exception) {
        return new CommonResult<>(444, "服务不可用V2", exception.getClass().getCanonicalName());
    }
}
