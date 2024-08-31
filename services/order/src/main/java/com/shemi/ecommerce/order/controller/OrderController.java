package com.shemi.ecommerce.order.controller;

import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import com.shemi.ecommerce.order.service.impl.OrderServiceImpl;
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
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl service;

    @PostMapping
    public ResponseEntity<Map<String, OrderResponse>> createOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        Map<String,OrderResponse> data= new HashMap<>();
        data.put("data",service.createOrder(request));
        return ResponseEntity.ok(data);
    }
}
