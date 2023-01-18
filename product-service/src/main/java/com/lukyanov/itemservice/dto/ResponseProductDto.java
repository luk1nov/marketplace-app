package com.lukyanov.itemservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponseProductDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Long sellerId;
    private final String status;
    private final ResponseCategoryDto responseCategoryDto;
    private final String condition;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime created;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updated;
}
