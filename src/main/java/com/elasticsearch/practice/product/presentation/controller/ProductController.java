package com.elasticsearch.practice.product.presentation.controller;

import com.elasticsearch.practice.product.application.response.ResDTO;
import com.elasticsearch.practice.product.application.response.ResProductGetDTO;
import com.elasticsearch.practice.product.application.response.ResProductPostDTO;
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


        // TODO : elasticsearch 와 연동하여 검색된 결과값을 반환하는 로직을 추가해주세요.

        return null;
    }

}
