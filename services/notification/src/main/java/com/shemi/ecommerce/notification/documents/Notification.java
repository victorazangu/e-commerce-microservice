package com.shemi.ecommerce.notification.documents;


import com.shemi.ecommerce.kafka.payment.record.PaymentConfirmation;
import com.shemi.ecommerce.notification.enums.NotificationType;
import com.shemi.ecommerce.kafka.order.record.OrderConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentCorfirmation;
}
