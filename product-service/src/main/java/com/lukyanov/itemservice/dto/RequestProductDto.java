package com.lukyanov.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;
    @NotBlank
    @Size(min = 2)
    private String description;
    @PositiveOrZero
    private BigDecimal price;
    private Long categoryId;
    @NotBlank
    @Size(min = 2, max = 255)
    private String condition;
}
