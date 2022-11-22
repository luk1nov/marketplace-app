package com.lukyanov.itemservice.controller;

import com.lukyanov.itemservice.dto.RequestItemDto;
import com.lukyanov.itemservice.dto.ResponseItemDto;
import com.lukyanov.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Value("${welcome.message}")
    private String testString;

    @GetMapping
    public List<ResponseItemDto> findAll(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("findAllItemsCircuitBreaker");
        return circuitBreaker.run(itemService::findAll);
    }

    @GetMapping("/{id}")
    public ResponseItemDto findById(@PathVariable UUID id){
        return itemService.findById(id);
    }

    @PostMapping
    public ResponseItemDto create(@RequestBody RequestItemDto responseItemDto){
        return itemService.create(responseItemDto);
    }

    @PutMapping("/{id}")
    public ResponseItemDto update(@RequestBody RequestItemDto requestItemDto, @PathVariable UUID id){
        return itemService.update(id, requestItemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        itemService.remove(id);
    }

    @GetMapping("/test")
    public String test(){
        return testString;
    }
}
