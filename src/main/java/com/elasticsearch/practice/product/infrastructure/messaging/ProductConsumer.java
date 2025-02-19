package com.elasticsearch.practice.product.infrastructure.messaging;

import com.elasticsearch.practice.product.application.service.ProductElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "ProductConsumer")
public class ProductConsumer {

    private final ProductElasticService productElasticService;

    // TODO : elasticsearch-practice 가 구독하는 이벤트를 처리하는 로직을 개발해주세요.

}
