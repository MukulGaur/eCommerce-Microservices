package com.ecommerce_microservices.inventory_service.controller;

import com.ecommerce_microservices.inventory_service.exception.InventoryNotFoundException;
import com.ecommerce_microservices.inventory_service.model.Inventory;
import com.ecommerce_microservices.inventory_service.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Inventory management APIs")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    @Operation(summary = "Get inventory by product ID", description = "Get inventory by product ID")
    public ResponseEntity<Inventory> checkInventory(@PathVariable Long productId) {
        Optional<Inventory> inventory = Optional.ofNullable(inventoryService.findByProductId(productId));
        return inventory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update inventory by product ID", description = "Update inventory by product ID")
    public Inventory updateInventory(@PathVariable Long productId, @RequestParam int quantity) {
        return inventoryService.updateInventory(productId, quantity);
    }

    @PostMapping("/reserve")
    @Operation(summary = "Reserve inventory by product ID", description = "Reserve inventory by product ID")
    public Inventory reserveInventory(@RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.reserveInventory(productId, quantity);
    }

    @PostMapping("/release")
    @Operation(summary = "Release inventory by product ID", description = "Release inventory by product ID")
    public Inventory releaseInventory(@RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.releaseInventory(productId, quantity);
    }
}
