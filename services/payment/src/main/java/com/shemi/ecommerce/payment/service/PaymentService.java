package com.shemi.ecommerce.payment.service;

import com.shemi.ecommerce.payment.record.PaymentRequest;
import jakarta.validation.Valid;

public interface PaymentService {
    public Integer createPayment(PaymentRequest request);
}
