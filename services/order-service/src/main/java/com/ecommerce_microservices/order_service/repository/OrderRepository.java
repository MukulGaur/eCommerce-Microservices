package com.ecommerce_microservices.order_service.repository;

import com.ecommerce_microservices.order_service.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    public List<Orders> findOrdersByUserId(Long userId);
}
