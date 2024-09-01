package com.shemi.ecommerce.order.repository;

import com.shemi.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,Integer> {
}
