package com.shemi.ecommerce.order.mapper;

import com.shemi.ecommerce.order.entity.Order;
import com.shemi.ecommerce.order.enums.PaymentMethod;
import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import com.shemi.ecommerce.product.record.PurchaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId(),
                null

        );
    }

}
