package com.shemi.ecommerce.product.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message ="Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity must be positive")
        Double quantity

) {
}
