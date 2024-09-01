package com.shemi.ecommerce.orderline.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity must be positive")
        Double quantity
) {
}
