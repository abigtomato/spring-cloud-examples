package com.abigtomato.example.client;

import com.abigtomato.example.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "seata-storage-service")
public interface StorageClient {

    @PostMapping(value = "/storage/decrease")
    CommonResult<String> decrease(@RequestParam("productId") Long productId,
                                  @RequestParam("count") Integer count);
}
