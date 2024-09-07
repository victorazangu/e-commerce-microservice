package com.shemi.ecommerce.orderline.service;

import com.shemi.ecommerce.orderline.entity.OrderLine;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;
import com.shemi.ecommerce.orderline.record.OrderLineResponse;

import java.util.List;


public interface OrderLineService {
   public OrderLine saveOrderLine(OrderLineRequest orderLineRequest);
   public List<OrderLineResponse> getOrderLineByOrderId(Integer id);

}
