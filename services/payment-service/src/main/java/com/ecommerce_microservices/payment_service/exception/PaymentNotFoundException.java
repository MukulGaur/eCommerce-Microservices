package com.ecommerce_microservices.payment_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(Long orderId){
        super("Payment with order id '%s' not found".formatted(orderId));
    }
}
