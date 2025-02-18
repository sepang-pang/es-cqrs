package com.elasticsearch.practice.product.infrastructure.messaging;

import com.elasticsearch.practice.product.application.service.ProductElasticService;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import com.elasticsearch.practice.product.infrastructure.util.EventSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "ProductConsumer")
public class ProductConsumer {

    private final ProductElasticService productElasticService;

    @KafkaListener(topics = "create-product-event", groupId = "elasticsearch-practice-group")
    public void consume(String message) {
        ProductMessageDTO dto = EventSerializer.deserialize(message, ProductMessageDTO.class);
        productElasticService.createProductDocument(dto);
    }
}
