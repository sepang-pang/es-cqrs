package com.elasticsearch.practice.product.domain.entity;

import com.elasticsearch.practice.product.domain.constraint.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public ProductEntity(String name, String description, BigDecimal price, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public static ProductEntity create(String name, String description, BigDecimal price, ProductStatus status) {
        return ProductEntity.builder()
                .name(name)
                .description(description)
                .price(price)
                .status(status)
                .build();
    }
}
