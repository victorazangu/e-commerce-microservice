package com.shemi.ecommerce.orderline.service;

import com.shemi.ecommerce.orderline.entity.OrderLine;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;


public interface OrderLineService {
   public OrderLine saveOrderLine(OrderLineRequest orderLineRequest);

}
