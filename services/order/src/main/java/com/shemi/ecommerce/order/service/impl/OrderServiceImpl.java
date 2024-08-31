package com.shemi.ecommerce.order.service.impl;

import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    public OrderResponse createOrder(@Valid OrderRequest request) {
    }
}
