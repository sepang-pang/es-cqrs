package com.elasticsearch.practice.product.application.service;

import com.elasticsearch.practice.product.application.response.ResProductPostDTO;
import com.elasticsearch.practice.product.domain.constraint.ProductStatus;
import com.elasticsearch.practice.product.domain.constraint.ProductTopic;
import com.elasticsearch.practice.product.domain.entity.ProductEntity;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import com.elasticsearch.practice.product.infrastructure.repository.ProductRepository;
import com.elasticsearch.practice.product.presentation.request.ReqProductPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final KafkaService kafkaService;

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

        // 상품 생성 이벤트 발행
        kafkaService.send(ProductTopic.CREATE_PRODUCT.getTopic(), ProductMessageDTO.of(productEntityForSaving));

        return ResProductPostDTO.of(productEntityForSaving);
    }
}
