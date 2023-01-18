package com.lukyanov.itemservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductStatusDto {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 55)
    private String status;
}
