package com.shemi.ecommerce.order.record;

import com.shemi.ecommerce.order.enums.PaymentMethod;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId,
List<PuchaseRequest> products
) {
}
