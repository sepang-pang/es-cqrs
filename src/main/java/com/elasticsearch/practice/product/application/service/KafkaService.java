package com.elasticsearch.practice.product.application.service;

import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;

public interface KafkaService {

    void send(String topic, ProductMessageDTO dto);
}
