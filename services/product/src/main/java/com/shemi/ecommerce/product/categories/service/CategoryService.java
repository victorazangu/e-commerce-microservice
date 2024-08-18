package com.shemi.ecommerce.product.categories.service;


import com.shemi.ecommerce.product.categories.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategory(Integer id);
    public Category updateCategory(Category category,Integer id);
    public String deleteCategory(Integer id);
}
