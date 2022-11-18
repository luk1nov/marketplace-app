package com.lukyanov.itemservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class CategoryDto implements Serializable {
    private Long id;
    private final String name;
    private final String description;
    private final Set<CategoryDto> subcategories;
}
