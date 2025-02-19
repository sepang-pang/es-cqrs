package com.elasticsearch.practice.product.infrastructure.service;

import com.elasticsearch.practice.product.application.response.ResProductGetDTO;
import com.elasticsearch.practice.product.application.service.ProductElasticService;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductElasticServiceImpl implements ProductElasticService {

    public void createProductDocument(ProductMessageDTO dto) {
        // TODO : Document 를 저장하는 로직을 구현해주세요.
    }

    public ResProductGetDTO searchProductDocument(Pageable pageable, String name, Long minPrice, Long maxPrice, String status, String sort) {
        // TODO : Document  를 동적으로 검색하는 로직을 구현해주세요.

        return null;
    }

}
