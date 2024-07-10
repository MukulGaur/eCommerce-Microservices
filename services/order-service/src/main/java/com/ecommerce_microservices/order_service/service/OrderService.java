package com.ecommerce_microservices.order_service.service;

import com.ecommerce_microservices.order_service.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Orders createOrder(Orders order);
    Orders getOrderById(Long id);
    List<Orders> getOrdersByUserId(Long userId);
    List<Orders> getAllOrders();
    Optional<Orders> updateOrder(Long id, Orders order);
    String deleteOrderById(Long id);

}
