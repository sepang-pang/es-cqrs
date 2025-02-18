package com.elasticsearch.practice.product.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqProductPostDTO {

    private Product product;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        @NotBlank(message = "상품 이름을 입력해주세요.")
        private String name;

        @NotBlank(message = "상품 설명을 입력해주세요.")
        private String description;

        @NotNull(message = "상품 가격을 입력해주세요.")
        private BigDecimal price;

    }
}
