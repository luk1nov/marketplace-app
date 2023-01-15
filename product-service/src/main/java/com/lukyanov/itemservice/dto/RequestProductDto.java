package com.lukyanov.itemservice.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RequestProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private String condition;
}
