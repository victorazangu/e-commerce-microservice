package com.shemi.ecommerce.kafka.order.record;

import com.shemi.ecommerce.kafka.customer.record.Customer;
import com.shemi.ecommerce.kafka.payment.enums.PaymentMethod;
import com.shemi.ecommerce.kafka.product.record.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

){

}
