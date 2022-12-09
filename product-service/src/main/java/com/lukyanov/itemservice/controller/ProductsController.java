package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;
import com.lukyanov.itemservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Value("${welcome.message}")
    private String testString;

    @GetMapping
    @PreAuthorize("hasAuthority('message.read')")
    public List<ResponseProductDto> findAll(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("findAllItemsCircuitBreaker");
        return circuitBreaker.run(productService::findAll);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('message.read')")
    public ResponseProductDto findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto responseItemDto){
        return productService.create(responseItemDto);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@RequestBody RequestProductDto requestProductDto, @PathVariable Long id){
        return productService.update(id, requestProductDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.remove(id);
    }

    @GetMapping("/test")
    public String test(){
        return testString;
    }
}
