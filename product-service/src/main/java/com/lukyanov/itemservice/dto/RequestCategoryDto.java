package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategoryDto {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 55)
    private String name;
    @NotBlank
    @Size(min = 2)
    private String description;
    private Long parentCategoryId;
}
