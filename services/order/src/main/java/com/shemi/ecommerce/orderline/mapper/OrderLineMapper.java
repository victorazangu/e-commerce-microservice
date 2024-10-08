package com.shemi.ecommerce.orderline.mapper;

import com.shemi.ecommerce.order.entity.Order;
import com.shemi.ecommerce.orderline.entity.OrderLine;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;
import com.shemi.ecommerce.orderline.record.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
