package com.shemi.ecommerce.kafka.consumers;

import com.shemi.ecommerce.email.service.EmailService;
import com.shemi.ecommerce.kafka.order.record.OrderConfirmation;
import com.shemi.ecommerce.kafka.payment.record.PaymentConfirmation;
import com.shemi.ecommerce.notification.documents.Notification;
import com.shemi.ecommerce.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
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
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Received Payment Confirmation: {}", paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentCorfirmation(paymentConfirmation)
                        .build()
        );
        var customerNane = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        var email = paymentConfirmation.customerEmail();
        emailService.sendPaymentSuccessEmail(
                email,
                customerNane,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Received Order Confirmation: {}", orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var name = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        var email = orderConfirmation.customer().email();
        var amount = orderConfirmation.totalAmount();
        var ref = orderConfirmation.orderReference();
        var products = orderConfirmation.products();
        emailService.sendOrderConfirmationEmail(
                email,
                name,
                amount,
                ref,
                products
        );
    }

}
