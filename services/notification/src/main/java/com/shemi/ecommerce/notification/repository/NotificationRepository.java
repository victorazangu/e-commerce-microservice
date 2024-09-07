package com.shemi.ecommerce.notification.repository;

import com.shemi.ecommerce.notification.documents.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
