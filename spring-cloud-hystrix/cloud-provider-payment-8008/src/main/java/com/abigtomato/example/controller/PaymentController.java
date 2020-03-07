package com.abigtomato.example.controller;

import com.abigtomato.example.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoOk(id);
        log.info(result);
        return result;
    }

    @GetMapping("/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoTimeout(id);
        log.info(result);
        return result;
    }

    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info(result);
        return result;
    }
}
