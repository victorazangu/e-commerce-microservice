package com.shemi.ecommerce.kafka.consumers;

import com.shemi.ecommerce.kafka.order.record.OrderConfirmation;
import com.shemi.ecommerce.kafka.payment.record.PaymentConfirmation;
import com.shemi.ecommerce.notification.documents.Notification;
import com.shemi.ecommerce.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.shemi.ecommerce.notification.enums.NotificationType.ORDER_CONFIRMATION;
import static com.shemi.ecommerce.notification.enums.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
//    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Received Payment Confirmation: {}", paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentCorfirmation(paymentConfirmation)
                        .build()

        );

        // todo send email
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Received Order Confirmation: {}", orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()

        );

        // todo send email
    }

}
