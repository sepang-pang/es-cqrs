package com.elasticsearch.practice.product.application.service;

import com.elasticsearch.practice.product.application.response.ResProductGetDTO;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import org.springframework.data.domain.Pageable;

public interface ProductElasticService {

    void createProductDocument(ProductMessageDTO dto);

    ResProductGetDTO searchProductDocument(Pageable pageable, String name, Long minPrice, Long maxPrice, String status, String sort);
}
