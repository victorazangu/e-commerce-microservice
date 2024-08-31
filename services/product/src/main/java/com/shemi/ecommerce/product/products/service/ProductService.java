package com.shemi.ecommerce.product.products.service;

import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.record.ProductPurchaseRequest;
import com.shemi.ecommerce.product.products.record.ProductPurchaseResponse;
import com.shemi.ecommerce.product.products.record.ProductRequest;
import com.shemi.ecommerce.product.products.record.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse getProduct(Integer productId);
    public List<ProductResponse> getAllProducts();
    public Product createProduct(ProductRequest product);
    public Product updateProduct(Product product, Integer productId);
    public String deleteProduct(Integer productId);
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> body);
}
