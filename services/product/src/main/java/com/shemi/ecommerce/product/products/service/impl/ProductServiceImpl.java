package com.shemi.ecommerce.product.products.service.impl;

import com.shemi.ecommerce.product.exceptions.EntityNotFoundException;
import com.shemi.ecommerce.product.exceptions.ProductNotFoundException;
import com.shemi.ecommerce.product.exceptions.ProductPurchaseException;
import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.mapper.ProductMapper;
import com.shemi.ecommerce.product.products.record.ProductPurchaseRequest;
import com.shemi.ecommerce.product.products.record.ProductPurchaseResponse;
import com.shemi.ecommerce.product.products.record.ProductRequest;
import com.shemi.ecommerce.product.products.record.ProductResponse;
import com.shemi.ecommerce.product.products.repository.ProductRepository;
import com.shemi.ecommerce.product.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    public final ProductRepository productRepository;
    @Autowired
    public ProductMapper productMapper;

    @Override
    public ProductResponse getProduct(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() ->
                        new EntityNotFoundException("Product not found"));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(x -> productMapper.toProductResponse(x))
                .collect(Collectors.toList());
    }


    @Override
    public Product createProduct(ProductRequest product) {
        Product req = ProductMapper.productRequestToProduct(product);
        return productRepository.save(req);

    }

    @Override
    public Product updateProduct(Product product, Integer productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));
        Product updatedProduct = mergeProduct(existingProduct, product);
        return productRepository.save(updatedProduct);
    }

    @Override
    public String deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return String.format("Product with id %s deleted successfully", productId);
    }

    Product mergeProduct(Product existing, Product newProduct) {
        if (newProduct.getName() != null) {
            existing.setName(newProduct.getName());
        }
        if (newProduct.getDescription() != null) {
            existing.setDescription(newProduct.getDescription());
        }
        if (newProduct.getAvailableQuantity() != null) {
            existing.setAvailableQuantity(newProduct.getAvailableQuantity());
        }
        if (newProduct.getPrice() != null) {
            existing.setPrice(newProduct.getPrice());
        }
        if (newProduct.getCategory() != null) {
            existing.setCategory(newProduct.getCategory());
        }

        return existing;
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> body) {
        var productIds = body.
                stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }
        var storedRequest = body
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productsRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productsRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + productsRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productsRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productsRequest.quantity()));

        }
        return purchasedProducts;
    }
}
