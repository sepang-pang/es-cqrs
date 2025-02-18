package com.elasticsearch.practice.product.domain.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductTopic {

    CREATE_PRODUCT(Topic.CREATE_PRODUCT);

    private final String topic;

    public static class Topic {
        public static final String CREATE_PRODUCT = "create-product-event";
    }
}
