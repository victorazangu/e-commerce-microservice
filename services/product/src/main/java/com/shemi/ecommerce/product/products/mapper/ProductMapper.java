package com.shemi.ecommerce.product.products.mapper;

import com.shemi.ecommerce.product.categories.entity.Category;
import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.record.ProductRequest;
import com.shemi.ecommerce.product.products.record.ProductResponse;

public class ProductMapper {
    public static Product productRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(
                        Category
                                .builder()
                                .id(productRequest.categoryId())
                                .build())
                .availableQuantity(productRequest.availabilityQuantity())
                .build();
    }

    public  static ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder().build();
    }

}
