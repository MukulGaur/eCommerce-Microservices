package com.ecommerce_microservices.payment_service.controller;

import com.ecommerce_microservices.payment_service.exception.PaymentNotFoundException;
import com.ecommerce_microservices.payment_service.model.Payment;
import com.ecommerce_microservices.payment_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Payment management APIs")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    @Operation(summary = "Process payment for the given order ID", description = "Process payment for the given order ID")
    public Payment processPayment(@RequestParam Long orderId, @RequestParam double amount) {
        return paymentService.processPayment(orderId, amount);
    }

    @GetMapping("/status/{orderId}")
    @Operation(summary = "Get payment status for the given order ID", description = "Get payment status for the given order ID")
    public Payment getPaymentStatus(@PathVariable Long orderId) {
        return paymentService.getPaymentStatus(orderId)
                .orElseThrow(() -> new PaymentNotFoundException(orderId));
    }
}
