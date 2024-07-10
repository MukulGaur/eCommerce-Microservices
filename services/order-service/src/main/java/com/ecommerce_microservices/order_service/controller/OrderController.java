package com.ecommerce_microservices.order_service.controller;

import com.ecommerce_microservices.order_service.model.Orders;
import com.ecommerce_microservices.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order management APIs")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create new Order", description = "Create new order")
    public Orders createOrder(@RequestBody Orders order){
        return orderService.createOrder(order);
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve all orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve order by ID")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id){
        Optional<Orders> order = Optional.ofNullable(orderService.getOrderById(id));
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get order by User Id", description = "Retrieve order by User Id")
    public ResponseEntity<List<Orders>> getOrderByUserId(@PathVariable Long userId){
        Optional<List<Orders>> listOfOrdersByUser = Optional.ofNullable(orderService.getOrdersByUserId(userId));
        return listOfOrdersByUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order by ID", description = "Update order by ID")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Orders order){
        Optional<Orders> updatedOrder = orderService.updateOrder(id, order);
        return updatedOrder.map(u -> ResponseEntity.ok("Order updated successfully"))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID", description = "Delete order by ID")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.deleteOrderById(id));
    }
}
