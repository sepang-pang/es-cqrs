package com.elasticsearch.practice.product.application.response;

import com.elasticsearch.practice.product.domain.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResProductPostDTO {

    private Product product;

    public static ResProductPostDTO of(ProductEntity productEntity) {
        return ResProductPostDTO.builder()
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
        private String status;

        public static Product from(ProductEntity productEntity) {
            return Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .description(productEntity.getDescription())
                    .price(productEntity.getPrice())
                    .status(productEntity.getStatus().getStatus())
                    .build();
        }
    }
}
