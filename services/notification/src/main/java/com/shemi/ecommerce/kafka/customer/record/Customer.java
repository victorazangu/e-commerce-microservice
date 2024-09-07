package com.shemi.ecommerce.kafka.customer.record;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
