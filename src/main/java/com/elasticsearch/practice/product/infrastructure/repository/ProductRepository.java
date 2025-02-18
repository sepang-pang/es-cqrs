package com.elasticsearch.practice.product.infrastructure.repository;

import com.elasticsearch.practice.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
