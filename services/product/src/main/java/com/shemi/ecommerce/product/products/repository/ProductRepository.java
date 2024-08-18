package com.shemi.ecommerce.product.products.repository;

import com.shemi.ecommerce.product.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
