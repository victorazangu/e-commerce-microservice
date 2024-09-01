package com.shemi.ecommerce.orderline.repository;

import com.shemi.ecommerce.orderline.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
