package com.lukyanov.itemservice.spec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCriteria {
    private String searchQuery;
    @Min(value = 1)
    private Long categoryId;
    @Min(value = 0)
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long conditionId;
    private Long sellerId;
}
