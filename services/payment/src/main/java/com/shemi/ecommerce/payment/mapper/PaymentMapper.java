package com.shemi.ecommerce.payment.mapper;

import com.shemi.ecommerce.payment.entity.Payment;
import com.shemi.ecommerce.payment.record.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
