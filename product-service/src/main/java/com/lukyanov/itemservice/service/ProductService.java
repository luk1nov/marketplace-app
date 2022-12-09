package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;

public interface ProductService extends BaseService<ResponseProductDto, Long>{
    ResponseProductDto create(RequestProductDto t);
    ResponseProductDto update(Long id, RequestProductDto t);
}
