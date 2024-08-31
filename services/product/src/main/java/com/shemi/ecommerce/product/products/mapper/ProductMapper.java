package com.shemi.ecommerce.product.products.mapper;

import com.shemi.ecommerce.product.categories.entity.Category;
import com.shemi.ecommerce.product.products.entity.Product;
import com.shemi.ecommerce.product.products.record.ProductPurchaseResponse;
import com.shemi.ecommerce.product.products.record.ProductRequest;
import com.shemi.ecommerce.product.products.record.ProductResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static Product productRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
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


    public ProductResponse toProductResponse(Product product) {
        return  new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()

        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, @NotNull(message = "Quantity is mandatory") Double quantity) {

        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );

    }
}
