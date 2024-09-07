package com.shemi.ecommerce.orderline.repository;

import com.shemi.ecommerce.orderline.entity.OrderLine;
import com.shemi.ecommerce.orderline.record.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
    List<OrderLine> findAllByOrderId(Integer id);
}
