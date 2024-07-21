package com.ecommerce_microservices.payment_service.service.impl;

import com.ecommerce_microservices.payment_service.model.Payment;
import com.ecommerce_microservices.payment_service.repository.PaymentRepository;
import com.ecommerce_microservices.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;


    @Override
    public Payment processPayment(Long orderId, double amount) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setStatus("pending");
        payment.setPaymentDate(new Date());
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentStatus(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
