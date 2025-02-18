package com.elasticsearch.practice.product.infrastructure.repository;

import com.elasticsearch.practice.product.infrastructure.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductDocument, Long> {

}
