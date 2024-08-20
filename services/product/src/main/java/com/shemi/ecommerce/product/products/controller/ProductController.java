package com.shemi.ecommerce.product.products.controller;

import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.mapper.ProductMapper;
import com.shemi.ecommerce.product.products.record.ProductPurchaseRequest;
import com.shemi.ecommerce.product.products.record.ProductPurchaseResponse;
import com.shemi.ecommerce.product.products.record.ProductRequest;
import com.shemi.ecommerce.product.products.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<Map<String, Product>> createProduct(@RequestBody @Valid ProductRequest body) {
        Map<String, Product> data = new HashMap<>();
        Product req = ProductMapper.productRequestToProduct(body);
        Product product = productService.createProduct(req);
        data.put("data", product);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Map<String, List<ProductPurchaseResponse>>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> body) {
        Map<String, List<ProductPurchaseRequest>> data = new HashMap<>();
        return  null;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Product>>> getAllProducts() {
        Map<String, List<Product>> data = new HashMap<>();
        List<Product> products = productService.getAllProducts();
        data.put("data", products);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Product>> getProductById(@PathVariable Integer id) {
        Map<String, Product> data = new HashMap<>();
        Product product = productService.getProduct(id);
        data.put("data", product);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Product>> updateProduct(@RequestBody Product body, @PathVariable Integer id) {
        Map<String, Product> data = new HashMap<>();
        Product product = productService.updateProduct(body, id);
        data.put("data", product);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Integer id) {
        Map<String, String> data = new HashMap<>();
        String deleteProduct = productService.deleteProduct(id);
        data.put("data", deleteProduct);
        return ResponseEntity.ok(data);
    }

}
