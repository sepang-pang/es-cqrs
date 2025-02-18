package com.elasticsearch.practice.product.domain.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {

    AVAILABLE(Status.AVAILABLE),
    OUT_OF_STOCK(Status.OUT_OF_STOCK),
    DISCONTINUED(Status.DISCONTINUED),;

    private final String status;

    public static class Status {
        public static final String AVAILABLE = "AVAILABLE";
        public static final String OUT_OF_STOCK = "OUT_OF_STOCK";
        public static final String DISCONTINUED = "DISCONTINUED";
    }

}
