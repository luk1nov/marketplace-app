package com.lukyanov.itemservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "product.status")
@Getter
@Setter
public class ProductStatusPropertyConfig {
    private String defaultStatus;
}
