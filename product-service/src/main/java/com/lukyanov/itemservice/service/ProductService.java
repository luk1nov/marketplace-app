package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.RequestProductDto;
import com.lukyanov.itemservice.dto.ResponseProductDto;
import com.lukyanov.itemservice.spec.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends BaseService<ResponseProductDto, Long>{
    ResponseProductDto create(RequestProductDto t, String authorizationHeader);
    ResponseProductDto update(Long id, RequestProductDto t);
    Page<ResponseProductDto> findAllWithParams(Pageable pageable, ProductSearchCriteria criteria);
}
