package com.elasticsearch.practice.product.infrastructure.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.elasticsearch.practice.product.application.response.ResProductGetDTO;
import com.elasticsearch.practice.product.application.service.ProductElasticService;
import com.elasticsearch.practice.product.infrastructure.document.ProductDocument;
import com.elasticsearch.practice.product.infrastructure.messaging.dto.ProductMessageDTO;
import com.elasticsearch.practice.product.infrastructure.repository.ProductElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductElasticServiceImpl implements ProductElasticService {

    private final ProductElasticRepository productElasticRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public void createProductDocument(ProductMessageDTO dto) {
        productElasticRepository.save(ProductDocument.create(
                        dto.getProduct().getId(),
                        dto.product.getName(),
                        dto.product.getDescription(),
                        dto.product.getPrice(),
                        dto.getProduct().getStatus()
                )
        );
    }

    public ResProductGetDTO searchProductDocument(Pageable pageable, String name, Long minPrice, Long maxPrice, String status, String sort) {

        BoolQuery.Builder boolQuery = QueryBuilders.bool();

        nameLike(name, boolQuery);

        statusEq(status, boolQuery);

        priceRange(minPrice, maxPrice, boolQuery);

        NativeQuery query = NativeQuery.builder()
                .withQuery(boolQuery.build()._toQuery())
                .withSort(getSortOptions(sort))
                .build();

        SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class);

        List<ProductDocument> productDocumentList = searchHits.stream()
                .map(SearchHit::getContent)
                .toList();

        return ResProductGetDTO.of(new PageImpl<>(productDocumentList, pageable, searchHits.getTotalHits()));
    }

    private static List<SortOptions> getSortOptions(String sort) {
        List<SortOptions> sortOptions = new ArrayList<>();

        if (sort != null) {
            if (sort.toLowerCase().contains("high")) { // 가격 높은 순 정렬
                sortOptions.add(SortOptions.of(
                        builder -> builder.field(
                                field -> field.field("productPrice").order(SortOrder.Desc)))
                );
            } else if (sort.toLowerCase().contains("low")) { // 가격 낮은 순 정렬
                sortOptions.add(SortOptions.of(
                        builder -> builder.field(
                                field -> field.field("productPrice").order(SortOrder.Asc))));
            }
        }

        boolean isOldest = sort != null && sort.toLowerCase().contains("oldest");

        sortOptions.add(SortOptions.of(
                builder -> builder.field(
                        field -> field.field("productId").order(isOldest ? SortOrder.Asc : SortOrder.Desc))
        ));

        return sortOptions;
    }

    private static void priceRange(Long minPrice, Long maxPrice, BoolQuery.Builder boolQuery) {
        if (minPrice != null || maxPrice != null) {
            Query priceQuery = QueryBuilders.range(
                    builder -> builder.number(
                            numberBuilder -> {
                                numberBuilder.field("productPrice");
                                if (minPrice != null) {
                                    numberBuilder.gte(minPrice.doubleValue());
                                }
                                if (maxPrice != null) {
                                    numberBuilder.lte(maxPrice.doubleValue());
                                }
                                return numberBuilder;
                            }));
            boolQuery.must(priceQuery);
        }
    }

    private static void statusEq(String status, BoolQuery.Builder boolQuery) {
        if (status != null && !status.isEmpty()) {
            boolQuery.filter(
                    builder -> builder.term(
                            termQuery -> termQuery.field("status.enum").value(status)
                    ));
        }
    }

    private static void nameLike(String name, BoolQuery.Builder boolQuery) {
        if (name != null && !name.isEmpty()) {
            boolQuery.filter(
                    builder -> builder.match(
                            matchQuery -> matchQuery.field("productName").query(name)
                    ));
        }
    }
}
