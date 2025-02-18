package com.elasticsearch.practice.product.infrastructure.document;

import com.elasticsearch.practice.product.domain.constraint.ProductStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "product")
public class ProductDocument {

    @Id
    private Long productId;

    private String productName;

    @Field(name = "product_description") // Field 를 설정하지 않으면 Java 변수명이 자동으로 필드명으로 치환됨.
    private String productDescription;

    private int productPrice;

    @Field(type = FieldType.Keyword)
    private ProductStatus status;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private OffsetDateTime createdAt;

    @Builder
    public ProductDocument(Long productId, String productName, String productDescription, int productPrice, ProductStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.status = status;
        this.createdAt = OffsetDateTime.now();
    }

    public static ProductDocument create(Long productId, String productName, String productDescription, int productPrice, ProductStatus status) {
        return ProductDocument.builder()
                .productId(productId)
                .productName(productName)
                .productDescription(productDescription)
                .productPrice(productPrice)
                .status(status)
                .build();
    }

}
