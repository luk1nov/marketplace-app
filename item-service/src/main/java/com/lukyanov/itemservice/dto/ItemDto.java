package com.lukyanov.itemservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ItemDto implements Serializable {
    private UUID id;
    private final String name;
    private final String description;
    private final LocalDateTime created;
    private final LocalDateTime updated;
    private final CategoryDto categoryDto;
    private final ConditionDto conditionDto;
}
