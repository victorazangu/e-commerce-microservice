package com.shemi.ecommerce.orderline.service.impl;

import com.shemi.ecommerce.orderline.entity.OrderLine;
import com.shemi.ecommerce.orderline.mapper.OrderLineMapper;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;
import com.shemi.ecommerce.orderline.record.OrderLineResponse;
import com.shemi.ecommerce.orderline.repository.OrderLineRepository;
import com.shemi.ecommerce.orderline.service.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineServiceImpl implements OrderLineService {
    private OrderLineRepository repository;
    private OrderLineMapper mapper;

    @Override
    public OrderLine saveOrderLine(OrderLineRequest request) {
        var order= mapper.toOrderLine(request);
        return repository.save(order);

    }

    public List<OrderLineResponse> getOrderLineByOrderId(Integer id) {
        return repository.findAllByOrderId(id)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());

    }
}
