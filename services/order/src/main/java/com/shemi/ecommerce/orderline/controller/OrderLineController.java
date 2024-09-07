package com.shemi.ecommerce.orderline.controller;

import com.shemi.ecommerce.orderline.record.OrderLineResponse;
import com.shemi.ecommerce.orderline.service.impl.OrderLineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineServiceImpl service;

    @GetMapping("/order/{id}")
    public ResponseEntity<Map<String , List<OrderLineResponse>>> getOrderLineByOrderId(
            @PathVariable Integer id
    ){
        Map<String, List<OrderLineResponse>> data = new HashMap<>();
        data.put("data",service.getOrderLineByOrderId(id));
        return ResponseEntity.ok(data);
    }
}
