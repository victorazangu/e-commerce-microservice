package com.shemi.ecommerce.order.service.impl;

import com.shemi.ecommerce.customer.clients.CustomerClient;
import com.shemi.ecommerce.exceptions.BusinessException;
import com.shemi.ecommerce.kafka.producers.OrderProducer;
import com.shemi.ecommerce.kafka.record.OrderConfirmation;
import com.shemi.ecommerce.order.mapper.OrderMapper;
import com.shemi.ecommerce.order.record.OrderRequest;
import com.shemi.ecommerce.order.record.OrderResponse;
import com.shemi.ecommerce.order.repository.OrderRepository;
import com.shemi.ecommerce.order.service.OrderService;
import com.shemi.ecommerce.orderline.record.OrderLineRequest;
import com.shemi.ecommerce.orderline.service.OrderLineService;
import com.shemi.ecommerce.orderline.service.impl.OrderLineServiceImpl;
import com.shemi.ecommerce.payment.clients.PaymentClient;
import com.shemi.ecommerce.payment.record.PaymentRequest;
import com.shemi.ecommerce.product.clients.ProductClient;
import com.shemi.ecommerce.product.record.PurchaseRequest;
import com.shemi.ecommerce.product.record.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineServiceImpl orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public OrderResponse createOrder(@Valid OrderRequest request) {
        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the id " + request.customerId()));
        var purchaseProducts = this.productClient.purchaseProduct(request.products());
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
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return mapper.toOrderResponse(order);
    }

    public List<OrderResponse> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer id) {
        return repository.findById(id)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));

    }
}
