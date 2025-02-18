package com.elasticsearch.practice.product.infrastructure.messaging.dto;

import com.elasticsearch.practice.product.domain.constraint.ProductStatus;
import com.elasticsearch.practice.product.domain.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMessageDTO {

    public Product product;

    public static ProductMessageDTO of(ProductEntity productEntity) {
        return ProductMessageDTO.builder()
                .product(Product.from(productEntity))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        private Long id;
        private String name;
        private String description;
        private int price;
        private ProductStatus status;

        public static Product from(ProductEntity productEntity) {
            return Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .description(productEntity.getDescription())
                    .price(productEntity.getPrice())
                    .status(productEntity.getStatus())
                    .build();
        }
    }
}
