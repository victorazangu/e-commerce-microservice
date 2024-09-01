package com.shemi.ecommerce.order.record;

import com.shemi.ecommerce.order.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message ="Customer should not be empty")
        @NotBlank(message = "Customer should not be blank")
        String customerId,
        @NotEmpty(message ="Products should not be empty")
        List<OrderRequest> products
) {
}
