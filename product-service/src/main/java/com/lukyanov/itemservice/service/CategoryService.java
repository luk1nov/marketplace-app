package com.lukyanov.itemservice.service;

import com.lukyanov.itemservice.dto.RequestCategoryDto;
import com.lukyanov.itemservice.dto.ResponseCategoryDto;

public interface CategoryService extends BaseService<ResponseCategoryDto, Long> {
    ResponseCategoryDto create(RequestCategoryDto t);
    ResponseCategoryDto update(Long id, RequestCategoryDto t);
}
