package com.shemi.ecommerce.product.categories.repository;

import com.shemi.ecommerce.product.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {
}
