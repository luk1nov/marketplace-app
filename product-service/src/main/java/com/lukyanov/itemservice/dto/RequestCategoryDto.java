package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategoryDto {
    private Long id;
    private String name;
    private String description;
    private Long parentCategoryId;
}
