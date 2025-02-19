package com.elasticsearch.practice.product.application.service;

import com.elasticsearch.practice.product.application.response.ResProductPostDTO;
import com.elasticsearch.practice.product.domain.constraint.ProductStatus;
import com.elasticsearch.practice.product.domain.entity.ProductEntity;
import com.elasticsearch.practice.product.infrastructure.repository.ProductRepository;
import com.elasticsearch.practice.product.presentation.request.ReqProductPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ResProductPostDTO createProduct(ReqProductPostDTO dto) {

        // 상품 생성
        ProductEntity productEntityForSaving = ProductEntity.create(
                dto.getProduct().getName(),
                dto.getProduct().getDescription(),
                dto.getProduct().getPrice(),
                ProductStatus.AVAILABLE
        );

        // 상품 저장
        productRepository.save(productEntityForSaving);

        // TODO : 상품 생성 이벤트를 처리하는 로직을 구현해주세요.

        return ResProductPostDTO.of(productEntityForSaving);
    }
}
