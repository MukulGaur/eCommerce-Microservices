package com.ecommerce_microservices.order_service.service.impl;

import com.ecommerce_microservices.order_service.exception.OrderNotFoundException;
import com.ecommerce_microservices.order_service.model.Orders;
import com.ecommerce_microservices.order_service.repository.OrderRepository;
import com.ecommerce_microservices.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> updateOrder(Long id, Orders order) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Orders existingOrder = optionalOrder.get();
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setTotalPrice(order.getTotalPrice());
            existingOrder.setProductId(order.getProductId());
            existingOrder.setUserId(order.getUserId());
            return Optional.of(orderRepository.save(existingOrder));
        } else{
            throw new OrderNotFoundException(id);
        }
    }

    @Override
    public String deleteOrderById(Long id) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            orderRepository.deleteById(id);
            return "Order with id '%s' is deleted.".formatted(id);
        } else{
            throw new OrderNotFoundException(id);
        }
    }
}
