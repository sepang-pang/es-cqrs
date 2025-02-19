package com.elasticsearch.practice.product.infrastructure.messaging;

import com.elasticsearch.practice.product.application.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // TODO : 상품 생성 이벤트를 처리하는 로직을 구현해주세요.

}
