package com.elasticsearch.practice.product.infrastructure.messaging;

import com.elasticsearch.practice.product.application.service.KafkaService;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import com.elasticsearch.practice.product.infrastructure.util.EventSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, ProductMessageDTO dto) {
        kafkaTemplate.send(topic, EventSerializer.serialize(dto));
    }
}
