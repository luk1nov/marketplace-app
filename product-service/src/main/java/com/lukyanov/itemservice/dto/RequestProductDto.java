package com.lukyanov.itemservice.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RequestProductDto {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final String condition;
}
