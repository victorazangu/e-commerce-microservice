package com.shemi.ecommerce.order.mapper;

import com.shemi.ecommerce.order.entity.Order;
import com.shemi.ecommerce.order.record.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(@Valid OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();

    }
}
