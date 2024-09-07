package com.shemi.ecommerce.payment.service.impl;

import com.shemi.ecommerce.notification.producers.NotificationProducer;
import com.shemi.ecommerce.notification.record.PaymentNotificationRequest;
import com.shemi.ecommerce.payment.mapper.PaymentMapper;
import com.shemi.ecommerce.payment.record.PaymentRequest;
import com.shemi.ecommerce.payment.repository.PaymentRepository;
import com.shemi.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
