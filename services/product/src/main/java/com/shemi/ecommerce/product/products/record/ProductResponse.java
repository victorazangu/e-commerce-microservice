package com.shemi.ecommerce.product.products.record;

import com.shemi.ecommerce.product.categories.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double availabilityQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
