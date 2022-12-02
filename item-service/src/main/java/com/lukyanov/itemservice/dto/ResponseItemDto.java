package com.lukyanov.itemservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponseItemDto implements Serializable {
    private Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final LocalDateTime created;
    private final LocalDateTime updated;
    private final CategoryDto categoryDto;
    private final ConditionDto conditionDto;
}
