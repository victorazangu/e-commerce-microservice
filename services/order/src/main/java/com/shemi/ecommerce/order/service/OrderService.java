package com.shemi.ecommerce.order.service;

import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {
    public OrderResponse createOrder(@Valid OrderRequest request);
    public List<OrderResponse> getAllOrders();
    public OrderResponse findOrderById(Integer id);
}
