package com.shemi.ecommerce.payment.record;

import com.shemi.ecommerce.customer.record.CustomerResponse;
import com.shemi.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
