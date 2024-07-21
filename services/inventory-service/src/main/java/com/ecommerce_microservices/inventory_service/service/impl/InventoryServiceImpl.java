package com.ecommerce_microservices.inventory_service.service.impl;

import com.ecommerce_microservices.inventory_service.exception.InventoryNotFoundException;
import com.ecommerce_microservices.inventory_service.model.Inventory;
import com.ecommerce_microservices.inventory_service.repository.InventoryRepository;
import com.ecommerce_microservices.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
    }

    @Override
    public Inventory updateInventory(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory reserveInventory(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
        if (inventory.getQuantity() < quantity) {
            throw new InventoryNotFoundException("Insufficient stock");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory releaseInventory(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }
}
