package com.elasticsearch.practice.product.presentation.controller;

import com.elasticsearch.practice.product.application.response.ResDTO;
import com.elasticsearch.practice.product.application.response.ResProductGetDTO;
import com.elasticsearch.practice.product.application.response.ResProductPostDTO;
import com.elasticsearch.practice.product.application.service.ProductElasticService;
import com.elasticsearch.practice.product.application.service.ProductService;
import com.elasticsearch.practice.product.presentation.request.ReqProductPostDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductElasticService productElasticService;

    @PostMapping("/products")
    public ResponseEntity<ResDTO<ResProductPostDTO>> createProduct(@RequestBody @Valid ReqProductPostDTO dto) {

        return new ResponseEntity<>(
                ResDTO.<ResProductPostDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("상품 생성에 성공하였습니다.")
                        .data(productService.createProduct(dto))
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/products")
    public ResponseEntity<ResDTO<ResProductGetDTO>> searchProduct(Pageable pageable,
                                                                  @RequestParam(name = "name", required = false) String name,
                                                                  @RequestParam(name = "minPrice", required = false) Long minPrice,
                                                                  @RequestParam(name = "maxPrice", required = false) Long maxPrice,
                                                                  @RequestParam(name = "status", required = false) String status,
                                                                  @RequestParam(name = "sort", required = false) String sort) {

        return new ResponseEntity<>(
                ResDTO.<ResProductGetDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품 검색에 성공하였습니다.")
                        .data(productElasticService.searchProductDocument(
                                pageable, name, minPrice, maxPrice, status, sort
                        ))
                        .build(),
                HttpStatus.OK
        );
    }

}
