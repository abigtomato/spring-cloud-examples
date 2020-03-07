package com.abigtomato.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/provider")
public class ProviderController {

    @GetMapping("/ok/{id}")
    public String getOk(@PathVariable("id") Integer id) {
        return id.toString();
    }

    @GetMapping("/v2/ok/{id}")
    public String getOkV2(@PathVariable("id") Integer id) {
        return id.toString();
    }
}
