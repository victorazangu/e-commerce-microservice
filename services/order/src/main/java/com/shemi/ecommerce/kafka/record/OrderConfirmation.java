package com.shemi.ecommerce.kafka.record;

import com.shemi.ecommerce.customer.record.CustomerResponse;
import com.shemi.ecommerce.enums.PaymentMethod;
import com.shemi.ecommerce.product.record.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
