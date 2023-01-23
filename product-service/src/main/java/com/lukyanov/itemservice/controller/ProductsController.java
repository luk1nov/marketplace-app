package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;
import com.lukyanov.itemservice.service.ProductService;
import com.lukyanov.itemservice.spec.ProductSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public Page<ResponseProductDto> findAll(Pageable pageable, @Valid ProductSearchCriteria productSearchCriteria){
        return productService.findAllWithParams(pageable, productSearchCriteria);
    }

    @GetMapping("/{id}")
    public ResponseProductDto findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseProductDto create(@RequestBody @Valid RequestProductDto requestProductDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return productService.create(requestProductDto, authHeader);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@RequestBody @Valid RequestProductDto requestProductDto, @PathVariable Long id){
        return productService.update(id, requestProductDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.remove(id);
    }
}
