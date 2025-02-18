package com.elasticsearch.practice.product.application.response;

import com.elasticsearch.practice.product.infrastructure.document.ProductDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResProductGetDTO {

    public ProductPage productPage;

    public static ResProductGetDTO of(Page<ProductDocument> productDocumentPage) {
        return ResProductGetDTO.builder()
                .productPage(ProductPage.from(productDocumentPage))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductPage {

        private List<Product> productList;
        private PageDetails page;

        public static ProductPage from(Page<ProductDocument> productDocumentPage) {
            return ProductPage.builder()
                    .productList(Product.from(productDocumentPage.getContent()))
                    .page(PageDetails.from(productDocumentPage))
                    .build();
        }
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
        private OffsetDateTime createdAt;

        public static List<Product> from(List<ProductDocument> ProductDocumentList) {
            return ProductDocumentList.stream()
                    .map(Product::from)
                    .toList();
        }

        public static Product from(ProductDocument productDocument) {
            return Product.builder()
                    .id(productDocument.getProductId())
                    .name(productDocument.getProductName())
                    .description(productDocument.getProductDescription())
                    .price(productDocument.getProductPrice())
                    .status(productDocument.getStatus().getStatus())
                    .createdAt(productDocument.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageDetails {

        private int size;
        private int number;
        private long totalElements;
        private int totalPages;

        public static PageDetails from(Page<ProductDocument> productDocumentPage) {
            return PageDetails.builder()
                    .size(productDocumentPage.getSize())
                    .number(productDocumentPage.getNumber())
                    .totalElements(productDocumentPage.getTotalElements())
                    .totalPages(productDocumentPage.getTotalPages())
                    .build();
        }
    }
}
