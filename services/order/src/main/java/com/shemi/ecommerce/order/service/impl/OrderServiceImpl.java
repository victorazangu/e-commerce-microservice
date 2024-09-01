package com.shemi.ecommerce.order.service.impl;

import com.shemi.ecommerce.customer.clients.CustomerClient;
import com.shemi.ecommerce.exceptions.BusinessException;
import com.shemi.ecommerce.order.mapper.OrderMapper;
import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import com.shemi.ecommerce.order.repository.OrderRepository;
import com.shemi.ecommerce.order.service.OrderService;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;
import com.shemi.ecommerce.orderline.service.OrderLineService;
import com.shemi.ecommerce.orderline.service.impl.OrderLineServiceImpl;
import com.shemi.ecommerce.product.clients.ProductClient;
import com.shemi.ecommerce.product.record.PurchaseRequest;
import com.shemi.ecommerce.product.record.PurchaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineServiceImpl orderLineService;

    public OrderResponse createOrder(@Valid OrderRequest request) {
        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the id " + request.customerId()));
        this.productClient.purchaseProduct(request.products());
        var order = this.repository.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start payment process -> payment ms-> TODO

        // send order confirmation --> notification ms -> TODO


        return null;
    }
}
