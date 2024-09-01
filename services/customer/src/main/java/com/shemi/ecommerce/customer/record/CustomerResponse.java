package com.shemi.ecommerce.customer.record;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
