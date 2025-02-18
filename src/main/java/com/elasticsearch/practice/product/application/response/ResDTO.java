package com.elasticsearch.practice.product.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResDTO<T> {

    private Integer code;
    private String message;
    private T data;

}
