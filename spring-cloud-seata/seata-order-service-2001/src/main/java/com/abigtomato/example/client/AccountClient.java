package com.abigtomato.example.client;

import com.abigtomato.example.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
@FeignClient(value = "seata-account-service")
public interface AccountClient {

    @PostMapping(value = "/account/decrease")
    CommonResult<Void> decrease(@RequestParam("userId") Long userId,
                                @RequestParam("money") BigDecimal money);
}
