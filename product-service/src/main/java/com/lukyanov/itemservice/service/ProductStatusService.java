package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.ProductStatusDto;

public interface ProductStatusService extends BaseService<ProductStatusDto, Long>{
    ProductStatusDto findByStatus(String status);

    ProductStatusDto getDefaultStatus();
}
