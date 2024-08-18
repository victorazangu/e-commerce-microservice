package com.shemi.ecommerce.product.categories.controller;


import com.shemi.ecommerce.product.categories.entity.Category;
import com.shemi.ecommerce.product.categories.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<Map<String,Category>> createCategory(@RequestBody @Valid Category category) {
        Map<String,Category> categoryMap = new HashMap<>();
        Category category1 = categoryService.createCategory(category);
        categoryMap.put("data",category1);
        return ResponseEntity.ok(categoryMap);
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Category>>> getAllCategories() {
        Map<String, List<Category>> categoryMap = new HashMap<>();
        List<Category> categories = categoryService.getAllCategories();
        categoryMap.put("data",categories);
        return ResponseEntity.ok(categoryMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity< Map<String,Category>> getCategoryById(@PathVariable Integer id) {
        Map<String,Category> categoryMap = new HashMap<>();
        Category category = categoryService.getCategory(id);
        categoryMap.put("data",category);
        return ResponseEntity.ok(categoryMap);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Category>> updateCategory(@RequestBody @Valid Category category,@PathVariable Integer id) {
        Map<String,Category> categoryMap = new HashMap<>();
        Category  updatedCategory = categoryService.updateCategory(category,id);
        categoryMap.put("data",updatedCategory);
        return ResponseEntity.ok(categoryMap);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }
}
