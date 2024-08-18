package com.shemi.ecommerce.product.products.service.impl;

import com.shemi.ecommerce.product.exceptions.ProductNotFoundException;
import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.repository.ProductRepository;
import com.shemi.ecommerce.product.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;
    @Override
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElseThrow(()->
                new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product, Integer productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()->
                new ProductNotFoundException("Product not found"));
        Product updatedProduct = mergeProduct(existingProduct,product);
        return productRepository.save(updatedProduct);
    }

    @Override
    public String deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return String.format("Product with id %s deleted successfully", productId);
    }

    Product mergeProduct(Product existing, Product newProduct) {
        if(newProduct.getName() != null){
            existing.setName(newProduct.getName());
        }
        if(newProduct.getDescription()!= null){
            existing.setDescription(newProduct.getDescription());
        }
        if(newProduct.getAvailableQuantity() != null){
            existing.setAvailableQuantity(newProduct.getAvailableQuantity());
        }
        if(newProduct.getPrice() != null){
            existing.setPrice(newProduct.getPrice());
        }
        if(newProduct.getCategory() != null){
            existing.setCategory(newProduct.getCategory());
        }

        return existing;
    }
}
