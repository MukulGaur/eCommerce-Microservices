package com.ecommerce_microservices.inventory_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(Long productId){
        super("Inventory with product id '%s' not found".formatted(productId));
    }

    public InventoryNotFoundException(String msg){
        super(msg);
    }
}
