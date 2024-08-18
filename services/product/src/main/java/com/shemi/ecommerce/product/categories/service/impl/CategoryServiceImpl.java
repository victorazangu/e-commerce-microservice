package com.shemi.ecommerce.product.categories.service.impl;

import com.shemi.ecommerce.product.categories.entity.Category;
import com.shemi.ecommerce.product.categories.repository.CategoryRepository;
import com.shemi.ecommerce.product.categories.service.CategoryService;
import com.shemi.ecommerce.product.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository productCategoryRepository;

    @Override
    public Category createCategory(Category category) {
        return productCategoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer id) {
        return productCategoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(String.format("Category with id %s not found", id)));
    }

    @Override
    public Category updateCategory(Category category, Integer id) {
        Category findCategory = productCategoryRepository.findById(id).orElseThrow(()->
                new CategoryNotFoundException(String.format("Category with id %s not found", id))) ;
        Category update = merge(findCategory,category);
       return productCategoryRepository.save(update);
    }

    @Override
    public String deleteCategory(Integer id) {
         productCategoryRepository.deleteById(id);
         return "Category with id " + id + " deleted";
    }

    Category merge(Category existingCategory, Category newCategory) {
        if (newCategory.getName() != null) {
            existingCategory.setName(newCategory.getName());
        }
        if (newCategory.getDescription() != null) {
            existingCategory.setDescription(newCategory.getDescription());
        }
        if (newCategory.getProducts() != null) {
            existingCategory.setProducts(newCategory.getProducts());
        }
        return existingCategory;
    }
}
