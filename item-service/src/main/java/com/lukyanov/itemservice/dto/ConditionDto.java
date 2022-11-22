package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConditionDto {
    private Long id;
    private final String name;
}
