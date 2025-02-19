package com.elasticsearch.practice.product.infrastructure.document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "product")
public class ProductDocument {

    // TODO : elasticsearch 에 저장될 Document 객체를 상세 구현해주세요.

}
