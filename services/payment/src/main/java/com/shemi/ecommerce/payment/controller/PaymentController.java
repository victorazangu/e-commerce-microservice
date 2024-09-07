package com.shemi.ecommerce.payment.controller;

import com.shemi.ecommerce.payment.record.PaymentRequest;
import com.shemi.ecommerce.payment.service.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl service;

    @PostMapping
    public ResponseEntity<Map<String,Integer>> createPayment(
            @RequestBody @Valid PaymentRequest request
    ){
        Map<String,Integer> data = new HashMap<>();
        data.put("id",service.createPayment(request));
        return ResponseEntity.ok(data);
    }
}
