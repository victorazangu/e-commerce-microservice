package com.shemi.ecommerce.product.products.service;

import com.shemi.ecommerce.product.products.entity.Product;

import java.util.List;

public interface ProductService {
    public Product getProduct(Integer productId);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Product updateProduct(Product product, Integer productId);
    public String deleteProduct(Integer productId);
}
