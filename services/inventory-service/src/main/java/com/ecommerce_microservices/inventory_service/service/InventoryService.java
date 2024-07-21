package com.ecommerce_microservices.inventory_service.service;

import com.ecommerce_microservices.inventory_service.model.Inventory;

import java.util.Optional;

public interface InventoryService {
    Inventory findByProductId(Long productId);
    Inventory updateInventory(Long productId, Integer quantity);
    Inventory reserveInventory(Long productId, Integer quantity);
    Inventory releaseInventory(Long productId, Integer quantity);
}
