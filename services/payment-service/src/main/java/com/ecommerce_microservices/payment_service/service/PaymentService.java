package com.ecommerce_microservices.payment_service.service;

import com.ecommerce_microservices.payment_service.model.Payment;

import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Long orderId, double amount);
    Optional<Payment> getPaymentStatus(Long orderId);
}
